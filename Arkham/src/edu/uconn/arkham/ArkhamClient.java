package edu.uconn.arkham;

import java.awt.*;
import java.awt.event.*;
import java.net.URL;

import javax.swing.*;
import javax.swing.border.*;

public class ArkhamClient extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8669245374795528022L;

	private static final int minHeight = 600;
	private static final int minWidth = 800;

	private AboutDialog aboutDialog;
	private JButton button;
	private JLabel label;
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem menuItem;
	private JPanel panel;
	private JScrollPane scrollPane;
	private JTextArea textArea;

	public ArkhamClient() {
		initComponents();
	}

	private void initComponents() {
		// Set the operation for the close button
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Set the minimum size of the frame
		setMinimumSize(new Dimension(minWidth, minHeight));

		// Disable frame resizing
		setResizable(true);

		// Set the frame title text
		setTitle("Arkham Client");

		// Initialize subclasses
		aboutDialog = new AboutDialog(this);

		// Create the menu bar
		menuBar = new JMenuBar();

		// Build the 'File' menu
		menu = new JMenu("File");
		menu.setMnemonic(KeyEvent.VK_F);

		// Build the menu items
		menuItem = new JMenuItem("Start Server");
		menuItem.setMnemonic(KeyEvent.VK_S);
		menu.add(menuItem);

		menuItem = new JMenuItem("Stop Server");
		menuItem.setMnemonic(KeyEvent.VK_T);
		menu.add(menuItem);

		menu.addSeparator();

		menuItem = new JMenuItem("Exit");
		menuItem.setMnemonic(KeyEvent.VK_X);
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Terminate the application
				System.exit(0);
			}
		});
		menu.add(menuItem);

		// Add the 'File' menu to the menu bar
		menuBar.add(menu);

		// Build the 'Help' menu
		menu = new JMenu("Help");
		menu.setMnemonic(KeyEvent.VK_H);

		// Build the menu items
		menuItem = new JMenuItem("About");
		menuItem.setMnemonic(KeyEvent.VK_A);
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Display the 'About' dialog
				aboutDialog.pack();
				aboutDialog.setLocationRelativeTo(rootPane);
				aboutDialog.setVisible(true);
			}
		});
		menu.add(menuItem);

		// Add the 'Help' menu to the menu bar
		menuBar.add(menu);

		// Set the menu bar for this frame
		setJMenuBar(menuBar);

		// Create the 'Activity' panel
		panel = new JPanel(new BorderLayout());
		panel.setBorder(new CompoundBorder(new EmptyBorder(10, 10, 10, 10), new CompoundBorder(BorderFactory.createTitledBorder("Activity"), new EmptyBorder(10, 10, 10, 10))));

		// Build the scroll pane with included text area
		textArea = new JTextArea();
		scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		panel.add(scrollPane, BorderLayout.CENTER);

		// Add the 'Activity' panel to the frame
		add(panel, BorderLayout.CENTER);

		// Create a composite panel to acommodate multiple inner panels
		JPanel compositePanel = new JPanel(new GridLayout(0, 2));

		// Create the 'Actions' panel
		panel = new JPanel(new FlowLayout(FlowLayout.LEADING, 10, 10));
		panel.setBorder(new CompoundBorder(new EmptyBorder(10, 10, 10, 10), BorderFactory.createTitledBorder("Actions")));

		// Build action buttons
		button = new JButton("Start");
		panel.add(button);

		button = new JButton("Stop");
		panel.add(button);

		button = new JButton("Clear");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Clear the 'Activity' text area
				textArea.setText("");
			}
		});
		panel.add(button);

		// Add the 'Actions' panel to the composite panel
		compositePanel.add(panel);

		// Create the 'Status' panel
		panel = new JPanel(new FlowLayout(FlowLayout.LEADING, 10, 10));
		panel.setBorder(new CompoundBorder(new EmptyBorder(10, 10, 10, 10), BorderFactory.createTitledBorder("Status")));
		label = new JLabel("Server Offline");
		panel.add(label);

		// Add the 'Status' panel to the composite panel
		compositePanel.add(panel);

		// Add the composite panel to the frame
		add(compositePanel, BorderLayout.PAGE_END);

		// Prepare (size) the frame for display
		pack();

		// Center the frame on the screen
		setLocationRelativeTo(null);
	}

	public static void main(String[] args) {

		// Use the system look and feel for the frame
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new ArkhamClient().setVisible(true);
			}
		});
	}

	private class AboutDialog extends JDialog {

		/**
		 * 
		 */
		private static final long serialVersionUID = -3915681842999918798L;

		private ImageIcon icon;
		private JButton button;
		private JLabel label;
		private JPanel panel;

		protected AboutDialog(JFrame frame) {
			super(frame);
			initComponents();
		}

		private void initComponents() {
			// Set the operation for the close button
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

			// Set dialog modality
			setModalityType(ModalityType.APPLICATION_MODAL);

			// Disable dialog resizing
			setResizable(false);

			// Set the dialog title text
			setTitle("About");

			// Create the splash image
			icon = createImageIcon("/edu/uconn/assets/Arkham.jpg", null);
			label = new JLabel(icon);

			// Add the splash image to the dialog
			add(label, BorderLayout.PAGE_START);

			// Create the information panel
			panel = new JPanel(new GridLayout(0, 1, 5, 5));
			panel.setBorder(new CompoundBorder(new EmptyBorder(10, 10, 5, 10), new CompoundBorder(BorderFactory.createTitledBorder(""), new EmptyBorder(5, 5, 5, 5))));

			// Build the information panel
			label = new JLabel("Arkham Client 1.0");
			label.setFont(new Font(label.getFont().getName(), Font.BOLD, label.getFont().getSize()));
			panel.add(label);
			label = new JLabel("Antonio Cusano, Eugene Sanzi, Alberto De la Rosa Algarin, Joseph Gilbert");
			panel.add(label);

			// Add the information panel to the dialog
			add(panel, BorderLayout.CENTER);

			// Create the footer panel
			panel = new JPanel();
			panel.setBorder(new EmptyBorder(0, 0, 5, 0));

			// Build the footer panel
			button = new JButton("Close");
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// Dispose (close) the dialog
					dispose();
				}
			});
			panel.add(button);

			// Add the footer panel to the dialog
			add(panel, BorderLayout.PAGE_END);
		}

		protected ImageIcon createImageIcon(String path, String description) {
			URL imgURL = getClass().getResource(path);
			if(imgURL != null) {
				return new ImageIcon(imgURL, description);
			} else {
				return null;
			}
		}
	}

}
