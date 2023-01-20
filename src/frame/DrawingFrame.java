package frame;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import controller.DrawingController;
import view.DrawingView;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Panel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;

public class DrawingFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private DrawingView view;
	private DrawingController controller;

	private JPanel contentPane;
	private JPanel panel;
	private Panel menuPanel;

	private FrameMenuBar topMenuBar;
	private FrameTopToolBar topToolBar;
	private FrameLeftToolBar leftToolBar;
	private Panel panelWest;
	private Panel panelSouth;
	private Panel panelNorth;
	private Panel panelEast;
	private JPanel panelOption;
	private JScrollPane scrollPane;
	private JList<String> list;
	private DefaultListModel<String> defaultListModel = new DefaultListModel<>();

	public DrawingFrame() {
		view = new DrawingView();
		contentPane = new JPanel();
		topMenuBar = new FrameMenuBar();
		topToolBar = new FrameTopToolBar();
		leftToolBar = new FrameLeftToolBar();

		setTitle("IT25-2017 Sara Lazarevic");
		view.setBackground(Color.WHITE);
		view.setLayout(new BorderLayout(0, 0));

		addTopMenuBar();
		setUpContentPane();
		addViewMouseListener();
		addPanels();
		addTopToolBar();
		setUpScrollPane();
		addLeftToolBar();
	}

	private void setUpContentPane() {
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(view, BorderLayout.CENTER);
	}

	private void addPanels() {
		menuPanel = new Panel();
		menuPanel.setBackground(new Color(245, 246, 247));
		contentPane.add(menuPanel, BorderLayout.NORTH);

		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		view.add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		panelSouth = new Panel();
		panelSouth.setPreferredSize(new Dimension(10, 5));
		panelSouth.setBackground(new Color(199, 209, 225));
		panel.add(panelSouth, BorderLayout.SOUTH);

		panelNorth = new Panel();
		panelNorth.setPreferredSize(new Dimension(10, 5));
		panelNorth.setBackground(new Color(199, 209, 225));
		panel.add(panelNorth, BorderLayout.NORTH);

		panelEast = new Panel();
		panelEast.setPreferredSize(new Dimension(5, 10));
		panelEast.setBackground(new Color(199, 209, 225));
		panel.add(panelEast, BorderLayout.EAST);

		panelWest = new Panel();
		panelWest.setPreferredSize(new Dimension(5, 10));
		panelWest.setBackground(new Color(199, 209, 225));
		getContentPane().add(panelWest, BorderLayout.WEST);

		panelOption = new JPanel();
		panelOption.setMaximumSize(new Dimension(100, 32767));
		panelOption.setSize(new Dimension(100, 0));
		panelOption.setBackground(new Color(245, 246, 247));
		getContentPane().add(panelOption, BorderLayout.EAST);
	}

	private void addTopToolBar() {
		topToolBar.setPreferredSize(new Dimension(13, 63));
		topToolBar.setAlignmentY(1.0f);
		topToolBar.setAlignmentX(1.0f);
		topToolBar.setBackground(new Color(245, 246, 247));
		contentPane.add(topToolBar, BorderLayout.NORTH);
	}

	private void setUpScrollPane() {
		scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.SOUTH);
		list = new JList<String>();
		list.setBackground(Color.WHITE);
		list.setModel(defaultListModel);
		scrollPane.setViewportView(list);
		scrollPane.setBounds(586, 452, 784, 100);
	}

	private void addViewMouseListener() {
		view.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.mouseClicked(e);
			}
		});
	}

	private void addTopMenuBar() {
		topMenuBar.setAlignmentY(3.0f);
		topMenuBar.setAlignmentX(3.0f);
		topMenuBar.setForeground(Color.GRAY);
		topMenuBar.setBackground(new Color(245, 246, 247));
		topMenuBar.setVisible(true);
		setJMenuBar(topMenuBar);
	}

	private void addLeftToolBar() {
		leftToolBar.setOrientation(SwingConstants.VERTICAL);
		leftToolBar.setBackground(new Color(245, 246, 247));
		panelOption.add(leftToolBar);
	}

	public DrawingView getView() {
		return view;
	}

	public void setController(DrawingController controller) {
		this.controller = controller;
		topMenuBar.setController(controller);
		topToolBar.setController(controller);
		leftToolBar.setController(controller);
	}

	public Panel getMenuPanel() {
		return menuPanel;
	}

	public void setMenuPanel(Panel menuPanel) {
		this.menuPanel = menuPanel;
	}

	public FrameMenuBar getTopMenuBar() {
		return topMenuBar;
	}

	public void setTopMenuBar(FrameMenuBar topMenuBar) {
		this.topMenuBar = topMenuBar;
	}

	public FrameTopToolBar getTopToolBar() {
		return topToolBar;
	}

	public void setTopToolBar(FrameTopToolBar topToolBar) {
		this.topToolBar = topToolBar;
	}

	public FrameLeftToolBar getLeftToolBar() {
		return leftToolBar;
	}

	public void setLeftToolBar(FrameLeftToolBar leftToolBar) {
		this.leftToolBar = leftToolBar;
	}

	public JList<String> getList() {
		return list;
	}

	public void setList(JList<String> list) {
		this.list = list;
	}

	public DefaultListModel<String> getDefaultListModel() {
		return defaultListModel;
	}

	public void logCommandList(DefaultListModel<String> dlm) {
		this.defaultListModel = dlm;
	}

	public DrawingController getController() {
		return controller;
	}

	public void setView(DrawingView view) {
		this.view = view;
	}
}