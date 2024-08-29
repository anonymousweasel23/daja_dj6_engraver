package com.engraver.ui;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import com.fazecast.jSerialComm.SerialPort;

/**
 * @author weasel
 */
@SuppressWarnings("serial")
public class SerialPortSelectorDialog extends javax.swing.JDialog {

	//private SerialCommsFrameHandler sb;

	private javax.swing.JButton btnCancel;
	private javax.swing.JButton btnTest;
	private javax.swing.JButton btnOK;
	private javax.swing.JComboBox<String> cmbSerialPort;
	private javax.swing.JLabel lblSerialPortCombo;
	private javax.swing.JPanel panelButtons;
	private javax.swing.JPanel panelButtonsLeft;
	private javax.swing.JPanel panelButtonsRight;
	private javax.swing.JPanel panelMain;
	private javax.swing.JScrollPane scrollDescription;
	private javax.swing.JTextPane textPaneDescription;
	private javax.swing.JCheckBox chkDefaultPort;

	private String defaultPort;

	/**
	 * Creates new form SerialPortSelectorDialog
	 */
	public SerialPortSelectorDialog(java.awt.Frame parent, boolean modal) {
		super(parent, "Serial Port Selection", modal);
		initComponents();
	}

	@SuppressWarnings("unchecked")
	private void initComponents() {
		java.awt.GridBagConstraints gridBagConstraints;

		panelMain = new javax.swing.JPanel();
		lblSerialPortCombo = new javax.swing.JLabel();
		cmbSerialPort = new javax.swing.JComboBox<>();
		scrollDescription = new javax.swing.JScrollPane();
		textPaneDescription = new javax.swing.JTextPane();
		panelButtons = new javax.swing.JPanel();
		panelButtonsLeft = new javax.swing.JPanel();
		btnTest = new javax.swing.JButton();
		panelButtonsRight = new javax.swing.JPanel();
		btnOK = new javax.swing.JButton();
		btnCancel = new javax.swing.JButton();
		chkDefaultPort = new javax.swing.JCheckBox();

		setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
		setLocation(new java.awt.Point(100, 100));
		setName("serialSelectDialog"); // NOI18N
		getContentPane().setLayout(new java.awt.GridBagLayout());

		panelMain.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Select Engraver Serial Port"));
		panelMain.setLayout(new java.awt.GridBagLayout());

		lblSerialPortCombo.setLabelFor(cmbSerialPort);
		lblSerialPortCombo.setText("Serial Port");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHEAST;
		gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 10);
		panelMain.add(lblSerialPortCombo, gridBagConstraints);
		lblSerialPortCombo.getAccessibleContext().setAccessibleDescription("");

		cmbSerialPort.setModel(new javax.swing.DefaultComboBoxModel<>(getSerialPortNames()));
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.ipadx = 20;
		gridBagConstraints.ipady = 5;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
		panelMain.add(cmbSerialPort, gridBagConstraints);

		cmbSerialPort.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cmbSerialPortDefaultActionPerformed(e);
			}
		});

		scrollDescription.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollDescription.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollDescription.setAutoscrolls(true);
		scrollDescription.setHorizontalScrollBar(null);
		scrollDescription.setInheritsPopupMenu(true);

		textPaneDescription.setText("Select one of the available serial ports on the system and click 'Test' to confirm it is connected to the egraver. Select 'OK' to save changes or 'Cancel' to revert changes.");
		scrollDescription.setViewportView(textPaneDescription);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.ipadx = 10;
		gridBagConstraints.ipady = 10;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		gridBagConstraints.insets = new java.awt.Insets(0, 10, 10, 10);
		panelMain.add(scrollDescription, gridBagConstraints);

		chkDefaultPort.setText("Use as default and don't ask again");
		chkDefaultPort.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				chkDefaultPortActionPerformed(evt);
			}
		});
		//chkDefaultPort.setEnabled(false);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
		panelMain.add(chkDefaultPort, gridBagConstraints);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.ipadx = 20;
		gridBagConstraints.ipady = 20;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 10);
		getContentPane().add(panelMain, gridBagConstraints);

		panelButtons.setLayout(new java.awt.GridBagLayout());

		btnTest.setFont(new java.awt.Font("Liberation Sans", 1, 15)); // NOI18N
		btnTest.setText("Test");
		btnTest.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnTestActionPerformed(evt);
			}
		});
		panelButtonsLeft.add(btnTest);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
		panelButtons.add(panelButtonsLeft, gridBagConstraints);

		btnOK.setFont(new java.awt.Font("Liberation Sans", 1, 15)); // NOI18N
		btnOK.setText("OK");
		btnOK.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				buttonOKActionPerformed(evt);
			}
		});
		panelButtonsRight.add(btnOK);
		btnOK.setEnabled(false);

		btnCancel.setFont(new java.awt.Font("Liberation Sans", 1, 15)); // NOI18N
		btnCancel.setText("Cancel");
		btnCancel.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnCancelActionPerformed(evt);
			}
		});
		panelButtonsRight.add(btnCancel);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
		panelButtons.add(panelButtonsRight, gridBagConstraints);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.ipadx = 10;
		gridBagConstraints.weightx = 1.0;
		getContentPane().add(panelButtons, gridBagConstraints);

		setBounds(0, 0, 410, 330);
	}

	private String[] getSerialPortNames() {
		final SerialPort[] ports = SerialPort.getCommPorts();
		final List<String> portNames = Arrays.stream(ports).map(u -> u.getSystemPortName()).collect(Collectors.toList());
		return portNames.toArray(String[]::new);
	}

	@Override
	public void setVisible(boolean tf) {

		if (tf) {

			defaultPort = (String) ApplicationFrame.properties.getProperty(PropertiesHelper.DEFAULT_PORT);

			if (defaultPort == null)
				this.chkDefaultPort.setSelected(false);
			else {
				cmbSerialPort.setSelectedItem(defaultPort);
				chkDefaultPort.setSelected(true);
			}
		}

		super.setVisible(tf);
	}

	/**
	 * Test to see if we can communicate with the engraver at the selected port
	 * 
	 * @param evt
	 */
	private void btnTestActionPerformed(java.awt.event.ActionEvent evt) {                                        

		final String portName = cmbSerialPort.getSelectedItem().toString();

		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

		if (Engraver.serialPort.testPort(portName))  {
			setCursor(Cursor.getDefaultCursor());
			JOptionPane.showMessageDialog(this, "Connected successfully to engraver on port " + portName, "Success", JOptionPane.INFORMATION_MESSAGE);
			btnOK.setEnabled(true);
			//btnCancel.setEnabled(false);
		} else {
			setCursor(Cursor.getDefaultCursor());
			JOptionPane.showMessageDialog(this, "Could not connect to engraver on port " + portName, "Error!", JOptionPane.ERROR_MESSAGE);
			System.out.println(String.format("Failed to connect on port %s",portName));
			btnOK.setEnabled(false);
			btnCancel.setEnabled(true);
		}
	}                                       

	/**
	 * Set system to use current serial port and exit
	 * 
	 * @param evt
	 */
	private void buttonOKActionPerformed(java.awt.event.ActionEvent evt) {                                         
		Engraver.serialPort.openPort(cmbSerialPort.getSelectedItem().toString());
		Engraver.serialPort.setOsName(System.getProperties().getProperty("os.name"));
		setVisible(false);
		btnOK.setEnabled(false);
		btnCancel.setEnabled(true);
	}                                        

	/**
	 * Close frame without changing settings
	 * 
	 * @param evt
	 */
	private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {                                          
		setVisible(false);
		btnOK.setEnabled(false);
	}                                         

	/**
	 * Switch selected state of checkbox
	 * 
	 * @param evt
	 */
	private void cmbSerialPortDefaultActionPerformed(java.awt.event.ActionEvent evt) {                                          
		String newPort = (String)this.cmbSerialPort.getSelectedItem();
		if (newPort.equals(defaultPort)) 
			chkDefaultPort.setSelected(true);
		else
			chkDefaultPort.setSelected(false);

	}

	/**
	 * Write / clear default port from application settings
	 * 
	 * @param evt
	 */
	private void chkDefaultPortActionPerformed(ActionEvent evt) {
		if (chkDefaultPort.isSelected()) {
			String newPort = (String)this.cmbSerialPort.getSelectedItem();
			if (!newPort.equals(defaultPort)) {
				ApplicationFrame.properties.setProperty(PropertiesHelper.DEFAULT_PORT, newPort);
				defaultPort = newPort;
			} 
		} else {
			ApplicationFrame.properties.clearProperty(PropertiesHelper.DEFAULT_PORT);
			defaultPort = null;
		}

	}
}