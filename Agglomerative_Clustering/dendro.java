/* 
Authors
https://github.com/Manu1ND
https://github.com/Ichigo27
*/

// Draw Dendrogram

import java.util.ArrayList;
import java.util.List;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import javax.swing.JFrame;

public class dendro extends JFrame {
	double shiftX = 60, shiftY = 450, factor = 400, factorX, factorY;

	List<ArrayList<ArrayList>> cluster = new ArrayList<ArrayList<ArrayList>>();

	public dendro(List<ArrayList<ArrayList>> history, int numOfPoints, String method) {
		super(method);
		this.cluster = history;
		this.factorX = this.factor / (double) numOfPoints;
		this.factorY = this.factor / Double.parseDouble((String) history.get(history.size() - 1).get(2).get(2));
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	// draw left right and top line for cluster
	void drawLRT(double xl, double xr, double yl, double yr, double yt, Graphics2D g2) {
		String yLabel = Double.toString(Math.round(yt * 1000) / 1000.0);
		xl = shiftX + xl * factorX;
		xr = shiftX + xr * factorX;
		yl = shiftY - yl * factorY;
		yr = shiftY - yr * factorY;
		yt = shiftY - yt * factorY;
		g2.draw(new Line2D.Double(xl, yl, xl, yt)); // left
		g2.draw(new Line2D.Double(xl, yt, xr, yt)); // top
		g2.draw(new Line2D.Double(xr, yr, xr, yt)); // right
		g2.draw(new Line2D.Double(shiftX - 2, yt, shiftX + 2, yt)); // plot y scale
		g2.drawString(yLabel, (float) shiftX - 35, (float) yt + 5); // y label

	}

	void drawDendro(Graphics2D g2) {
		double xl, xr, yl, yr, yt, xLabelCord;
		String xLabel;
		for (ArrayList<ArrayList> block : this.cluster) {
			xl = Double.parseDouble((String) block.get(3).get(0));
			xr = Double.parseDouble((String) block.get(3).get(1));
			yl = Double.parseDouble((String) block.get(2).get(0));
			yr = Double.parseDouble((String) block.get(2).get(1));
			yt = Double.parseDouble((String) block.get(2).get(2));
			drawLRT(xl, xr, yl, yr, yt, g2);
			for (int i = 0; i < 2; i++) {
				if (block.get(i).size() == 1) {
					xLabel = (String) block.get(i).get(0);
					xLabelCord = shiftX + Double.parseDouble((String) block.get(3).get(i)) * factorX;
					g2.drawString(xLabel, (float) xLabelCord - 7, (float) shiftY + 15); // x label
				}
			}

		}
	}

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.draw(new Line2D.Double(shiftX, shiftY - factor, shiftX, shiftY)); // vertical
		g2.draw(new Line2D.Double(shiftX, shiftY, shiftX + factor + 15, shiftY)); // horizontal
		drawDendro(g2);
	}
}