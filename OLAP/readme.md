# OLAP

## Aim

Perform various OLAP operations such as slice, dice, drilldown, rollup,
pivot.

## Problem statement

Consider a data warehouse for a hospital where there are 3 dimensions.

1\) Doctor, 2) Patient, 3) Time and the two measures Count and Charge, where
charge is the fee that a doctor charges a patient for a visit.

## Star Schema

![Star_Schema](/OLAP/star_schema.png)

## OLAP Operations

### 1) Slice

```sql
SELECT fact_table.doctor_id, charge FROM fact_table  
INNER JOIN doctor ON fact_table.doctor_id = doctor.doctor_id  
WHERE doctor_name ='Grace Ritchie';
```

| DOCTOR_ID | CHARGE |
| - | - |
| 2  | 534534  |
| 2  | 91354  |

### 2) Dice

```sql
SELECT fact_table.doctor_id, charge FROM fact_table  
INNER JOIN doctor ON fact_table.doctor_id = doctor.doctor_id  
WHERE doctor_name = 'Grace Ritchie' AND patient_id = '1' OR patient_id = '3';
```

| DOCTOR_ID | CHARGE |
| - | - |
| 2  | 534534  |
| 2  | 91354  |

### 3) Rollup

```sql
SELECT year, SUM(charge) FROM fact_table  
INNER JOIN doctor ON fact_table.doctor_id = doctor.doctor_id  
INNER JOIN time ON fact_table.time_key = time.time_key  
WHERE doctor_name ='Grace Ritchie'   
GROUP BY year;
```

| YEAR | SUM(CHARGE) |
| - | - |
| 2021  | 534534  |
| 2020  | 91354  |

### 4) Drill Down

```sql
SELECT quarter, SUM(charge) FROM fact_table  
NATURAL JOIN doctor  
INNER JOIN time ON fact_table.time_key = time.time_key  
WHERE doctor_name ='Grace Ritchie'   
GROUP BY quarter;
```

| QUARTER | SUM(CHARGE) |
| - | - |
| Q1  | 534534  |
| Q3  | 91354  |

### 5) Pivot

```sql
SELECT doctor_name, SUM(charge) FROM fact_table  
INNER JOIN doctor ON fact_table.doctor_id = doctor.doctor_id  
GROUP BY doctor_name;
```

| DOCTOR_NAME | SUM(CHARGE) |
| - | - |
| Grace Ritchie  | 625888  |
| Manjunath Naik  | 2354680  |
| Glenn Maxwell  | 5454  |
| Amit Ramani  | 1071331  |

```sql
SELECT * FROM  
    (SELECT doctor_name, charge FROM fact_table  
    INNER JOIN doctor ON fact_table.doctor_id = doctor.doctor_id)  
    PIVOT  
    (SUM(charge) AS total_charge FOR (doctor_name) ¬¬
    IN ('Grace Ritchie' AS Grace_Ritchie, 'Manjunath Naik' AS Manjunath_Naik, 'Glenn Maxwell' AS Glenn_Maxwell, 'Amit Ramani' AS Amit_Ramani));
```

| GRACE_RITCHIE_TOTAL_CHARGE | MANJUNATH_NAIK_TOTAL_CHARGE | GLENN_MAXWELL_TOTAL_CHARGE | AMIT_RAMANI_TOTAL_CHARGE |
| - | - | - | - |
| 625888  | 2354680  | 5454  | 1071331  |

## Resources

-   [OLAP-PDF](OLAP.pdf)
-   [livesql](https://livesql.oracle.com/apex/livesql/s/lo1wbvqwsxy2h47fw5iw4e120)
