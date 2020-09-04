/*
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication2;

//import org.lwjgl.opengl.GL11;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.geom.Ellipse2D;
import java.util.List;
import java.util.ArrayList;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.io.File;
import java.util.Random;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author LEEALAN - April of 2017 -
 */

public class NewJFrame extends javax.swing.JFrame {

	/**
	 * Creates new form NewJFrame
	 */
	public NewJFrame() {
		this.setVisible(true);
		initComponents();
		// open on centre screen
		setLocationRelativeTo(null);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		canvas.setSize(this.getSize());
		g2 = (Graphics2D) canvas.getGraphics();
		originalFileDimension = canvas.getSize();
	}

	public void windowStateListener(WindowEvent e) {
		System.out.println("HERE");
		this.DrawAll();
	}

	public Graphics2D g2;
	private int x1, y1, x2, y2; // mouse presses
	private LineObject lineBuffer;
	public Dimension originalFileDimension;
	public ArrayList<SkeletonObject> structures = new ArrayList<SkeletonObject>();
	private static int defaultSnapValue = 10;

	private boolean radioLineClicked = false;

	public static Color drawColor = Color.BLACK;
	public static Color dragColor = Color.red;
	public static Color backgroundColor = new Color(255, 255, 255);

	private SkeletonObject currentDragObject = null;

	private final ArrayList<LineObject> lineContainer = new ArrayList();
	java.awt.event.ActionListener a;

	public ArrayList<LineObject> getLineContainer() {

		return (new ArrayList<LineObject>(lineContainer));

	}

	/**
	 * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The
	 * content of this method is always regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		btnGroupTools = new javax.swing.ButtonGroup();
		jProgressBar1 = new javax.swing.JProgressBar();
		jPanel2 = new javax.swing.JPanel();
		jTabbedPane1 = new javax.swing.JTabbedPane();
		jScrollPane3 = new javax.swing.JScrollPane();
		jPanel3 = new javax.swing.JPanel();
		FirstFloor = new javax.swing.JCheckBox();
		SecondFloor = new javax.swing.JCheckBox();
		jPanel1 = new javax.swing.JPanel();
		jPanel4 = new javax.swing.JPanel();
		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jLabelCoordinate = new javax.swing.JTextField();
		jLabelPosition = new javax.swing.JTextField();
		jPanel5 = new javax.swing.JPanel();
		canvas = new javax.swing.JPanel();
		Create = new javax.swing.JButton();
		jMenuBar1 = new javax.swing.JMenuBar();
		menuFile = new javax.swing.JMenu();
		menuNew = new javax.swing.JMenuItem();
		menuOpen = new javax.swing.JMenuItem();
		jSeparator2 = new javax.swing.JPopupMenu.Separator();
		//menuSaveAs = new javax.swing.JMenuItem();
		menuSave = new javax.swing.JMenuItem();
		jSeparator1 = new javax.swing.JPopupMenu.Separator();
		menuQuit = new javax.swing.JMenuItem();
		//menuEdit = new javax.swing.JMenu();
		//menuDelete = new javax.swing.JMenuItem();
		//menuCut = new javax.swing.JMenuItem();
		//menuPaste = new javax.swing.JMenuItem();
		//menuCopy = new javax.swing.JMenuItem();
		menuTools = new javax.swing.JMenu();
		radioLine = new javax.swing.JRadioButtonMenuItem();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("DEFINETELY NOT AREA SKETCH");
		getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

		this.addWindowStateListener(new java.awt.event.WindowStateListener() {
			@Override
			public void windowStateChanged(WindowEvent e) {
				windowStateListener(e);

			}
		});
		jPanel2.setBackground(new java.awt.Color(204, 204, 204));

		jTabbedPane1.setMaximumSize(new java.awt.Dimension(150, 32767));

		FirstFloor.setText("First Floor");
		FirstFloor.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				FirstFloorActionPerformed(evt);
			}
		});

		SecondFloor.setText("Second Floor");

		javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
		jPanel3.setLayout(jPanel3Layout);
		jPanel3Layout.setHorizontalGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel3Layout.createSequentialGroup().addContainerGap()
						.addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(SecondFloor, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)

								.addGroup(jPanel3Layout.createSequentialGroup()
										.addComponent(FirstFloor, javax.swing.GroupLayout.PREFERRED_SIZE, 155,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(0, 9, Short.MAX_VALUE)))
						.addContainerGap()));
		jPanel3Layout.setVerticalGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel3Layout.createSequentialGroup().addComponent(FirstFloor)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(SecondFloor)
						.addContainerGap(140, Short.MAX_VALUE)));

		jScrollPane3.setViewportView(jPanel3);

		jTabbedPane1.addTab("Structures", jScrollPane3);

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 176, Short.MAX_VALUE));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 243, Short.MAX_VALUE));

		jTabbedPane1.addTab("Layers", jPanel1);

		jLabel1.setText("Coords");

		jLabel2.setText("Pos");

		jLabelCoordinate.setText("jTextField1");

		jLabelPosition.setText("jTextField1");

		javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
		jPanel4.setLayout(jPanel4Layout);
		jPanel4Layout.setHorizontalGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel4Layout.createSequentialGroup()
						.addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 58,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jLabelCoordinate, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(25, 25, 25)
						.addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 62,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jLabelPosition, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(0, 286, Short.MAX_VALUE)));
		jPanel4Layout.setVerticalGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
						jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jLabelCoordinate, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jLabelPosition, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)));

		canvas.setBackground(backgroundColor);
		canvas.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
			public void mouseDragged(java.awt.event.MouseEvent evt) {
				canvasMouseDragged(evt);
			}

			public void mouseMoved(java.awt.event.MouseEvent evt) {
				canvasMouseMoved(evt);
			}
		});
		canvas.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent evt) {
				canvasMousePressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				canvasMouseReleased(evt);
			}
		});
		canvas.addComponentListener(new java.awt.event.ComponentAdapter() {
			public void componentResized(java.awt.event.ComponentEvent evt) {
				canvasComponentResized(evt);
			}
		});

		javax.swing.GroupLayout canvasLayout = new javax.swing.GroupLayout(canvas);
		canvas.setLayout(canvasLayout);
		canvasLayout.setHorizontalGroup(canvasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 0, Short.MAX_VALUE));
		canvasLayout.setVerticalGroup(canvasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 0, Short.MAX_VALUE));

		javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
		jPanel5.setLayout(jPanel5Layout);
		jPanel5Layout
				.setHorizontalGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
								jPanel5Layout.createSequentialGroup().addContainerGap()
										.addComponent(canvas, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addContainerGap()));
		jPanel5Layout.setVerticalGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel5Layout.createSequentialGroup().addContainerGap().addComponent(canvas,
						javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addContainerGap()));

		Create.setText("Create");
		Create.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				createActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
						Short.MAX_VALUE)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
						.addContainerGap()
						.addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 181,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
										jPanel2Layout.createSequentialGroup().addComponent(Create).addGap(52, 52, 52)

												.addContainerGap()))));
		jPanel2Layout
				.setVerticalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
								.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(jPanel2Layout.createSequentialGroup().addGap(33, 33, 33)
												.addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addGroup(jPanel2Layout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)

														.addComponent(Create))
												.addGap(0, 26, Short.MAX_VALUE))
										.addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)));

		getContentPane().add(jPanel2);

		menuFile.setText("File");
		menuFile.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				menuFileActionPerformed(evt);
			}
		});

		menuNew.setText("Clear");
		menuNew.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				menuNewActionPerformed(evt);
			}
		});
		menuFile.add(menuNew);

		menuOpen.setText("Open");
		menuOpen.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				menuOpenActionPerformed(evt);
			}
		});
		menuFile.add(menuOpen);
		menuFile.add(jSeparator2);
		// This is save as
		// menuSaveAs.setText("SaveAs");
		// menuFile.add(menuSaveAs);

		menuSave.setText("Save");
		menuSave.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				menuSaveActionPerformed(evt);
			}
		});
		menuFile.add(menuSave);
		menuFile.add(jSeparator1);

		menuQuit.setText("Exit");
		menuQuit.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				menuQuitActionPerformed(evt);
			}
		});
		menuFile.add(menuQuit);

		jMenuBar1.add(menuFile);
		/*
		 * THIS IS THE EDIT MENU menuEdit.setBorder(new javax.swing.border.MatteBorder(null)); menuEdit.setText("Edit");
		 * 
		 * menuDelete.setText("Delete"); menuEdit.add(menuDelete);
		 * 
		 * menuCut.setText("Cut"); menuEdit.add(menuCut);
		 * 
		 * menuPaste.setText("Paste"); menuEdit.add(menuPaste);
		 * 
		 * menuCopy.setText("Copy"); menuEdit.add(menuCopy);
		 * 
		 * jMenuBar1.add(menuEdit);
		 */
		menuTools.setText("Tools");
		menuTools.addChangeListener(new javax.swing.event.ChangeListener() {
			public void stateChanged(javax.swing.event.ChangeEvent evt) {
				menuToolsStateChanged(evt);
			}
		});

		btnGroupTools.add(radioLine);
		radioLine.setSelected(false);
		radioLine.setText("Line");
		radioLine.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				radioLineActionPerformed(evt);
			}
		});
		menuTools.add(radioLine);

		jMenuBar1.add(menuTools);

		setJMenuBar(jMenuBar1);

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void createActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		Random r = new Random();
		SkeletonObject object;
		if (this.FirstFloor.isSelected()) {
			object = new FirstFloor(this,
					new StartPoint(r.nextInt(canvas.getWidth()), r.nextInt(canvas.getHeight())));
			this.structures.add(object);
		}
		if (this.SecondFloor.isSelected()) {
			object = new SecondFloor(this,
					new StartPoint(r.nextInt(canvas.getWidth()), r.nextInt(canvas.getHeight())));
			this.structures.add(object);
		}
	}

	public JPanel getPanel() {
		return this.canvas;
	}

	private void menuNewActionPerformed(java.awt.event.ActionEvent evt) {
		canvas.repaint();
		this.lineContainer.clear();
		this.structures.clear();
	}// GEN-LAST:event_menuNewActionPerformed

	private void menuOpenActionPerformed(java.awt.event.ActionEvent evt) {

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		int result = fileChooser.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			System.out.println("Selected file: " + selectedFile.getAbsolutePath());
			XMLDe.openXML(selectedFile.getAbsolutePath());
		}

		// String[] s = new String[2];
		// XMLDe.main(s);
	}

	private void menuOpenActionPerformed() {
		/*
		 * JFileChooser fileChooser = new JFileChooser(); fileChooser.setCurrentDirectory(new
		 * File(System.getProperty("user.home"))); int result = fileChooser.showOpenDialog(this); if (result ==
		 * JFileChooser.APPROVE_OPTION) { File selectedFile = fileChooser.getSelectedFile();
		 * System.out.println("Selected file: " + selectedFile.getAbsolutePath());
		 * XMLDe.main(selectedFile.getAbsolutePath()); }
		 */
		String[] s = new String[2];
		XMLDe.main(s);
	}

	private void menuQuitActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_menuQuitActionPerformed
		System.exit(0);
	}// GEN-LAST:event_menuQuitActionPerformed

	private void menuFileActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_menuFileActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_menuFileActionPerformed
	
	public void returnStroke(){
		g2.setStroke(new BasicStroke(2));
	}

	private void canvasMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_canvasMousePressed
		// TODO add your handling code here:
		this.returnStroke();
		if (g2 == null) {

			g2 = (Graphics2D) canvas.getGraphics();
		}
		x1 = roundPointTo(evt.getX(), defaultSnapValue);
		y1 = roundPointTo(evt.getY(), defaultSnapValue);
		for (SkeletonObject o : this.structures) {
			if (o.closeToCurrentPoint(x1, y1) || o.closeToStartPoint(x1, y1)) {
				this.currentDragObject = o;
				this.currentDragObject.startLineDraw((float) evt.getPoint().getX(), (float) evt.getPoint().getY());
				break;
			} else if (o.hasLineAt(x1, y1, 10)) {
				System.out.println("true");
			}
		}

		x2 = roundPointTo(evt.getX(), defaultSnapValue);
		y2 = roundPointTo(evt.getY(), defaultSnapValue);
	}// GEN-LAST:event_canvasMousePressed

	/**
	 * Rounds pointToRound to nearest roundTo multiple
	 */
	private int roundPointTo(int pointToRound, int roundTo) {
		int newValue = pointToRound;
		// float ratio = (float) pointToRound / (float) roundTo;
		// int rounded = Math.round(ratio);
		// newValue = rounded * roundTo;
		return newValue;
	}

	private void canvasMouseReleased(java.awt.event.MouseEvent evt) {
		if (this.currentDragObject != null) {
			this.currentDragObject.endLineDraw((float) evt.getPoint().getX(), (float) evt.getPoint().getY());
			this.currentDragObject = null;
			canvas.update(g2);
			DrawAll();
		}
		if (radioLine.isSelected()) {
			canvas.update(g2);
			x2 = roundPointTo(evt.getX(), defaultSnapValue);
			y2 = roundPointTo(evt.getY(), defaultSnapValue);

			lineBuffer = new LineObject((float) x1, (float) y1, (float) x2, (float) y2);
			lineContainer.add(lineBuffer);
			g2.setColor(drawColor);
			float[] center = lineBuffer.centreOfLine();
			g2.drawString(lineBuffer.CalculateLength() + "", center[0], center[1]);
			// System.out.println("Mouse Release");
			DrawAll();
		}
	}

	private void canvasMouseDragged(java.awt.event.MouseEvent evt) {

		if (this.currentDragObject != null) {
			this.currentDragObject.dragLine((float) evt.getPoint().getX(), (float) evt.getPoint().getY());
			DrawAll();

		}
		if (radioLine.isSelected()) {

			canvas.update(g2);
			x2 = roundPointTo(evt.getX(), defaultSnapValue);
			y2 = roundPointTo(evt.getY(), defaultSnapValue);

			lineBuffer = new LineObject((float) x1, (float) y1, (float) x2, (float) y2);
			// System.out.println("Mouse Dragged");
			DrawAll();
			g2.setColor(dragColor);
			g2.draw(lineBuffer);
			g2.drawString(lineBuffer.CalculateLength() + "", evt.getX(), evt.getY());

		}
		jLabelPosition.setText("Position is: " + evt.getX() + " , " + evt.getY());
	}

	private void canvasMouseMoved(java.awt.event.MouseEvent evt) {
		int x = evt.getX();
		int y = evt.getY();
		jLabelCoordinate.setText("Coordinate = " + x + " , " + y);

	}

	private void canvasComponentResized(java.awt.event.ComponentEvent evt) {

		g2 = (Graphics2D) canvas.getGraphics();
		Dimension newDimension = canvas.getSize();
		// System.out.println("Before: " + newDimension.height + " " + newDimension.width);
		scaleImages(newDimension);
		for (SkeletonObject o : this.structures) {
			o.scale(originalFileDimension, newDimension);
		}
		originalFileDimension = newDimension;
		// System.out.println(" After: " + originalFileDimension.height + " " + originalFileDimension.width);
		DrawAll();
	}

	public class StartPoint {

		public Ellipse2D startPoint;
		// Corner of square
		public double x, y;
		// Center of point
		public double xOrg, yOrg;
		private final float defaultSize = 10;

		public StartPoint(double x, double y) {
			this.xOrg = x;
			this.yOrg = y;
			this.x = x - (defaultSize / 2);
			this.y = y - (defaultSize / 2);
			startPoint = new Ellipse2D.Double(this.x, this.y, defaultSize, defaultSize);
			g2.draw(startPoint);
		}

		public void hide() {
			Color currentColor = g2.getColor();
			g2.setColor(canvas.getBackground());
			g2.draw(startPoint);
			g2.fill(startPoint);
			g2.setColor(currentColor);
		}

		public void show() {
			g2.draw(startPoint);
		}

		public boolean isSelected(Point2D point) {
			boolean isClicked = this.startPoint.contains(point);
			return isClicked;
		}

		public void highlight() {
			g2.fill(startPoint);
		}

		public void move(double x, double y) {
			this.xOrg = x;
			this.yOrg = y;
			this.x = x - (defaultSize / 2);
			this.y = y - (defaultSize / 2);
			startPoint = new Ellipse2D.Double(this.x, this.y, defaultSize, defaultSize);
			g2.draw(startPoint);
		}

		@Override
		public boolean equals(Object object) {
			if (!(object instanceof StartPoint)) {
				return false;
			}
			StartPoint otherStart = (StartPoint) object;
			if (otherStart.xOrg != this.xOrg)
				return false;
			if (otherStart.yOrg != this.yOrg)
				return false;
			if (otherStart.x != this.x)
				return false;
			if (otherStart.y != this.y)
				return false;
			return true;
		}

	}

	private void comboBoxColorActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_comboBoxColorActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_comboBoxColorActionPerformed

	private void menuSaveActionPerformed(java.awt.event.ActionEvent evt) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		int result = fileChooser.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			System.out.println("Selected file: " + selectedFile.getAbsolutePath());
			XMLEn.encodeXML(this, selectedFile.getAbsolutePath());
		}

	}// GEN-LAST:event_menuSaveActionPerformed

	private void menuToolsStateChanged(javax.swing.event.ChangeEvent evt) {// GEN-FIRST:event_menuToolsStateChanged
		// TODO add your handling code here:
	}// GEN-LAST:event_menuToolsStateChanged

	private void FirstFloorActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void radioLineActionPerformed(java.awt.event.ActionEvent evt) {
		if (radioLineClicked) {
			System.out.println("SELECTED");
			this.btnGroupTools.clearSelection();
		} else {
			this.radioLine.setSelected(true);
		}
		radioLineClicked = !radioLineClicked;
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		// <editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
		/*
		 * If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel. For details see
		 * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
		 */

		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		// </editor-fold>

		// Create and display the form
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				NewJFrame f = new NewJFrame();
			}
		});
	}

	public static NewJFrame getNewFrame() {
		return new NewJFrame();
	}

	private void scaleImages(Dimension currentDimension) {
		double heightRatio = (currentDimension.getHeight() / originalFileDimension.getHeight());
		double widthRatio = (currentDimension.getWidth() / originalFileDimension.getWidth());
		int i = 0;
		ArrayList<Line2D> temp = new ArrayList<Line2D>(lineContainer);
		for (Line2D line : temp) {
			double x1 = (line.getX1() * widthRatio);
			// x1 = this.roundPointTo((int) x1, defaultSnapValue);
			double x2 = (line.getX2() * widthRatio);
			// x2 = this.roundPointTo((int) x2, defaultSnapValue);
			double y1 = (line.getY1() * heightRatio);
			// y1 = this.roundPointTo((int) y1, defaultSnapValue);
			double y2 = (line.getY2() * heightRatio);
			// y2 = this.roundPointTo((int) y2, defaultSnapValue);
			// System.out.println("x1: " + x1 + " x2: " + x2 + " y1: " +y1 +" y2: " + y2);
			LineObject scaledLine = new LineObject((float) x1, (float) y1, (float) x2, (float) y2);
			this.lineContainer.remove(i);
			this.lineContainer.add(i, scaledLine);
			i++;
		}
	}

	public void DrawAll() {
		// System.out.println("Draw");
		g2.setColor(drawColor);
		// Draws plain lines
		for (int i = 0; i < lineContainer.size(); i++) {

			float[] ary = lineContainer.get(i).centreOfLine();
			g2.draw(lineContainer.get(i));
			g2.drawString(lineContainer.get(i).CalculateLength() + "", ary[0], ary[1]);
		}
		// Draw Objects
		for (SkeletonObject object : this.structures) {
			object.draw();
		}
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton Create;
	private javax.swing.JCheckBox FirstFloor;
	private javax.swing.JCheckBox SecondFloor;
	private javax.swing.ButtonGroup btnGroupTools;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JTextField jLabelCoordinate;
	private javax.swing.JTextField jLabelPosition;
	private javax.swing.JMenuBar jMenuBar1;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JPanel jPanel3;
	private javax.swing.JPanel jPanel4;
	private javax.swing.JPanel jPanel5;
	private javax.swing.JPanel canvas;
	private javax.swing.JProgressBar jProgressBar1;
	private javax.swing.JScrollPane jScrollPane3;
	private javax.swing.JPopupMenu.Separator jSeparator1;
	private javax.swing.JPopupMenu.Separator jSeparator2;
	private javax.swing.JTabbedPane jTabbedPane1;
	// private javax.swing.JMenuItem menuCopy;
	// private javax.swing.JMenuItem menuCut;
	// private javax.swing.JMenuItem menuDelete;
	// private javax.swing.JMenuItem menuPaste;
	// private javax.swing.JMenu menuEdit;
	private javax.swing.JMenu menuFile;
	private javax.swing.JMenuItem menuNew;
	private javax.swing.JMenuItem menuOpen;

	private javax.swing.JMenuItem menuQuit;
	private javax.swing.JMenuItem menuSave;
	// private javax.swing.JMenuItem menuSaveAs;
	private javax.swing.JMenu menuTools;
	private javax.swing.JRadioButtonMenuItem radioLine;
	// End of variables declaration//GEN-END:variables
}