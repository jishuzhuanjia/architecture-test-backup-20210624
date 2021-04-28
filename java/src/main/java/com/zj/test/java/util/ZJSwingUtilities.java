package com.zj.test.java.util;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.util.Enumeration;


/*
 * @author ZhouJian
 * 2018��3��9�� ����10:55:36
 *
 */
 /**
 *the class help create some common components and containers
 * also used to initialize swing frame
 * the method provide invoking for static methods
 * call directly
 * */
public class ZJSwingUtilities {
	
	public static final Color COLOR_TRANSPARENT = new Color(0,0,0,0);
	
	public static Cursor csrHover = new Cursor(Cursor.HAND_CURSOR);
	
	
	public static void main(String[] args)
	{
		JFrame frame = new JFrame();
		frame.setTitle("JTable����");
		frame.setSize((int) (500*0.618), 500);
		
		JTable table = new JTable();
		Object[] columnNames = {"column1","column2","column3","column4"};
		DefaultTableModel model = new DefaultTableModel(columnNames,0);
		model.addRow(new Object[]{"date1","date2","date3","date4"});
		model.addRow(new Object[]{"date1","date2","date3","date4"});
		model.addRow(new Object[]{"date1","date2","date3","date4"});
		model.addRow(new Object[]{"date1","date2","date3","date4"});
		model.addRow(new Object[]{"date1","date2","date3","date4"});
		table.setModel(model);
		
		table.setRowHeight(2, 100);
		table.setRowMargin(5);
		table.setIntercellSpacing(new Dimension(10,5));
		table.setAutoResizeMode(4);
		table.setDragEnabled(false);
		//table.setCellSelectionEnabled(false);
		table.setRowSelectionAllowed(false);
		
		JScrollPane sp = new JScrollPane();
		sp.setViewportView(table);
		frame.add(sp);
		PlafUtilities.centerWindow(frame);
		frame.setVisible(true);
	}
	
	
	
	/*
	 * JLable
	 * default setOpaque:bounds false
	 * if want to be opaque ,you need to
	 * invoke setOpaque(true) firstly
	 * 
	 * */

	/* JButton */
	/**
	 * Create the plain button
	 */
	public static JButton createPlainButton(ImageIcon icon, String text) 
	{
		JButton button = new JButton();
		button.setIcon(icon);
		button.setText(text);
		button.setContentAreaFilled(false);
		button.setFocusable(false);
		button.setBorder(null);
		return button;
		/*
		 * JButton button = new JButton(); button.setRequestFocusEnabled(false);
		 * button.setIcon(icon); button.setText(text);
		 * button.setContentAreaFilled(false); button.setFocusPainted(false);
		 * button.setBorderPainted(false);
		 */
	}

	/**
	 * Create the rich button
	 */
	public static JButton createRichButton(String text, 
			                               ImageIcon icon, 
			                               ImageIcon pressed, 
			                               ImageIcon hover,
			                               ImageIcon selected) 
	{
		JButton button = new JButton();
		button.setRolloverEnabled(true);
		button.setContentAreaFilled(false);
		button.setRequestFocusEnabled(false);
		button.setBorder(null);
		button.setIcon(icon);
		button.setSelectedIcon(selected);
		button.setRolloverIcon(hover);
		button.setPressedIcon(pressed);
		button.setText(text);
		return button;
	}

	/* JTree */
	/*
	 * ���췽��: 
	 * public JTable(TableModel dm) 
	 * public JTable(TableModel dm,TableColumnModel cm) 
	 * public JTable(TableModel dm, TableColumnModel m,ListSelectionModel sm 
	 * public JTable(int numRows, int numColumns) 
	 * public JTable(Vector rowData, Vector columnNames) 
	 * public JTable(final Object[][] rowData, final Object[] columnNames)
	 * 
	 * ��ͷ�� public void setTableHeader(JTableHeader tableHeader) 
	 * public JTableHeader getTableHeader()
	 * 
	 * ����أ� public void setRowHeight(int rowHeight)
	 *  public int getRowHeight()
	 * public void setRowHeight(int row, int rowHeight)ָ���и߶� 
	 * public void setRowMargin(int rowMargin)���������ͷ����һ�С���һ�о���
	 *  public int getRowMargin()
	 * public void setIntercellSpacing(Dimension intercellSpacing)���ڼ��
     *public Dimension getIntercellSpacing()
	 * 
	 * ����������ʾ��� public void setGridColor(Color gridColor)������ɫ 
	 * public Color getGridColor() 
	 * public void setShowGrid(boolean showGrid)//�Ƿ�������򶼲���ʾ
	 * public void setShowHorizontalLines(boolean showHorizontalLines) 
	 * public void setShowVerticalLines(boolean showVerticalLines)
	 * 
	 * public void setAutoResizeMode(int mode) AUTO_RESIZE_OFF = 0;
	 * AUTO_RESIZE_NEXT_COLUMN = 1; AUTO_RESIZE_SUBSEQUENT_COLUMNS = 2;
	 * AUTO_RESIZE_LAST_COLUMN = 3; AUTO_RESIZE_ALL_COLUMNS = 4; public int
	 * getAutoResizeMode()
	 * 
	 * public void setAutoCreateColumnsFromModel(boolean autoCreateColumnsFromModel)
	 *  public boolean getAutoCreateColumnsFromModel() 
	 *  public void createDefaultColumnsFromModel()��Model����ColumnModel
	 * �ڴ���ǰ���Ƴ�������(ʹ��getColumnModel)
	 * 
	 * public void setDefaultRenderer(Class<?> columnClass, TableCellRenderer
	 * renderer) 
	 * public TableCellRenderer getDefaultRenderer(Class<?> columnClass) 
	 * public void setDefaultEditor(Class<?> columnClass,TableCellEditor editor) 
	 * public TableCellEditor getDefaultEditor(Class<?> columnClass)
	 * 
	 * public final void setDropMode(DropMode dropMode) 
	 * public final DropMode getDropMode()
	 * 
	 * public void setAutoCreateRowSorter(boolean autoCreateRowSorter) 
	 * public boolean getAutoCreateRowSorter()
	 * 
	 * public void setUpdateSelectionOnSort(boolean update) 
	 * public boolean getUpdateSelectionOnSort() ����󽫱��ָ�����ԭ�������ϣ�ͨ������model������
	 * 
	 * public void setRowSorter(RowSorter<? extends TableModel> sorter) 
	 * public RowSorter<? extends TableModel> getRowSorter()
	 * 
	 * public void setSelectionMode(int selectionMode)
	 * ListSelectionModel.SINGLE_SELECTION
	 * ListSelectionModel.SINGLE_INTERVAL_SELECTION
	 * ListSelectionModel.MULTIPLE_INTERVAL_SELECTION
	 * 
	 * public void setRowSelectionAllowed(boolean rowSelectionAllowed) 
	 * public boolean getRowSelectionAllowed() 
	 * public void setColumnSelectionAllowed(boolean columnSelectionAllowed) 
	 * public boolean getColumnSelectionAllowed() 
	 * public void setCellSelectionEnabled(boolean cellSelectionEnabled)
	 * //�ƺ���setRowSelectionAllowedЧ����ͬ public boolean
	 * getCellSelectionEnabled()
	 * 
	 * ѡ����� public void selectAll()//Selects all rows, columns, and cells in the
	 * table. 
	 * public void clearSelection() 
	 * public void setRowSelectionInterval(int index0, int index1) 
	 * public void setColumnSelectionInterval(int index0, int index1)
	 *  public void addRowSelectionInterval(int index0, int index1) 
	 * public void addColumnSelectionInterval(int index0, int index1) 
	 * public void removeRowSelectionInterval(int index0, int index1)
	 *  public void removeColumnSelectionInterval(int index0, int index1) 
	 * public int getSelectedRow()//���ص�һ��ѡ������ -1-û��ѡ��
	 *  public int getSelectedColumn() 
	 *  public int[] getSelectedRows() 
	 * int[] getSelectedColumns() 
	 * int getSelectedRowCount() 
	 * int getSelectedColumnCount() 
	 * boolean isRowSelected(int row) 
	 * boolean isColumnSelected(int column) 
	 * boolean isCellSelected(int row, int column) 
	 * ��ɫ���
	 *  public Color getSelectionForeground()
	 *   public void setSelectionForeground(Color selectionForeground) 
	 * public Color getSelectionBackground() 
	 * public void setSelectionBackground(Color selectionBackground)
	 * 
	 * TableColumn getColumn(Object identifier) 
	 * convertColumnIndexToModel(int viewColumnIndex) 
	 * convertColumnIndexToView(int modelColumnIndex) 
	 * convertRowIndexToView(int modelRowIndex) 
	 * convertRowIndexToModel(int viewRowIndex)
	 * 
	 *  int getRowCount()
	 *  int getColumnCount()
	 *  String getColumnName(int column) 
	 *  public Class<?> getColumnClass(int column) 
	 * public Object getValueAt(int row, int column) 
	 *  public void setValueAt(Object aValue, int row, int column)
	 *  public boolean isCellEditable(int row, int column)
	 *  public void addColumn(TableColumn aColumn)
	 *  public void removeColumn(TableColumn aColumn)
	 *  public void moveColumn(int column, int targetColumn)
	 *  public int columnAtPoint(Point point)
	 *  public int rowAtPoint(Point point)
	 *  public Rectangle getCellRect(int row, int column, boolean includeSpacing)
	 *  public boolean editCellAt(int row, int column)
	 *  public boolean editCellAt(int row, int column, EventObject e)
	 *  public boolean isEditing()
	 *  public Component getEditorComponent() 
	 *  public int getEditingColumn()
	 *    public int getEditingRow()
	 *    
	 *    public void setModel(TableModel dataModel) 
	 *    public TableModel getModel()
	 *    public void setColumnModel(TableColumnModel columnModel)
	 * public TableColumnModel getColumnModel() 
	 * public void setSelectionModel(ListSelectionModel newModel)
	 * public ListSelectionModel getSelectionModel() 
	 * 
	 * 
	 */
	/**
	 * collapse all expanded treepaths
	 */
	public static void collapseTree(JTree tree) 
	{
		TreeNode rootNode = (TreeNode) tree.getModel().getRoot();
		TreePath rootPath = new TreePath(rootNode);

		if (rootNode.getChildCount() > 0) {
			for (Enumeration<?> e = rootNode.children(); e.hasMoreElements();) {
				TreeNode node = (TreeNode) e.nextElement();
				TreePath path = rootPath.pathByAddingChild(node);
				tree.collapsePath(path);
			}
		}
	}

	/* JTable */
	/**
	 * Hide the table header and return the old size 
	 */
	public static Dimension hideTableHeader(JTable table) 
	{
		Dimension old;
		old = table.getTableHeader().getPreferredSize();
		table.getTableHeader().setPreferredSize(new Dimension(0, 0));
		return old;
	}
	
	/**Ĭ��ΪFlowLayout*/
	public static JFrame createTestJFrame(String title,int width,int height) {
		JFrame testFrame = new JFrame();
		//����setUndecorated(false)�Ŀؼ�,���Ļ�ͼ���������е������ȥװ�ε�����ߴ�
		testFrame.setTitle(title);
		testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		testFrame.setSize(width, height);
		//center the frame
		testFrame.setLocationRelativeTo(null);
		return testFrame;
	}
	
	public static void initGlobalFont(Font globalFont) {
		FontUIResource fontResource = new FontUIResource(globalFont);
		for(Enumeration<Object> keys=UIManager.getDefaults().keys();keys.hasMoreElements();){
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			if(value instanceof FontUIResource) {
				UIManager.put(key, fontResource);
			}
		}
	}

	
}
