package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JSeparator;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

import Data.Database;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class View extends JFrame {

	private JPanel contentPane;
	private JTextField tfMaCT,tfTim,tfTenHK,tfSDT,tfChuyentau,tfNgaydi,tfSoghe,tfTinhtrang;
	private JTable table;
	public JComboBox cbToa;
	
	DefaultTableModel RecordTable;
	PreparedStatement pstm;
	Vector data,st;	
	int selectedrow=0;
	public void getData() {
		try {
			Connection conn = Database.connectDB();
			Statement stm=conn.createStatement();
			ResultSet rst=stm.executeQuery("select * from chuyentau");
			ResultSetMetaData rsmData=rst.getMetaData();
			int col = rsmData.getColumnCount();

			RecordTable = (DefaultTableModel)table.getModel();
			RecordTable.setRowCount(0);
			
			while (rst.next()) {
				   data = new Vector();
				   for (int i=1;i<=col;i++) {
				   data.add(rst.getString("mave"));
				   data.add(rst.getString("tenkhach"));
				   data.add(rst.getString("sdt"));
				   data.add(rst.getString("chuyentau"));
				   data.add(rst.getDate("ngaykhoihanh"));
				   data.add(rst.getInt("soghe"));
				   data.add(rst.getString("toa"));
				   data.add(rst.getString("tinhtrang"));
				   }
				   RecordTable.addRow(data);
				   table.getColumnModel().getColumn(0).setPreferredWidth(18);
				   table.getColumnModel().getColumn(5).setPreferredWidth(23);
				   table.getColumnModel().getColumn(6).setPreferredWidth(12);
				 }
			
			table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

				@Override
				public void valueChanged(ListSelectionEvent e) {
					if (table.getSelectedRow() >= 0) {
						tfMaCT.setText(table.getValueAt(table.getSelectedRow(),0)+"");
						tfTenHK.setText(table.getValueAt(table.getSelectedRow(),1)+"");
						tfSDT.setText(table.getValueAt(table.getSelectedRow(),2)+"");
						tfChuyentau.setText(table.getValueAt(table.getSelectedRow(),3)+"");
						tfNgaydi.setText(table.getValueAt(table.getSelectedRow(),4)+"");
						tfSoghe.setText(table.getValueAt(table.getSelectedRow(),5)+"");
						cbToa.setSelectedItem(table.getModel().getValueAt(table.getSelectedRow(),6)+"");
						tfTinhtrang.setText(table.getValueAt(table.getSelectedRow(),7)+"");
					}
				}
				
			});
			
			 conn.close();
			 rst.close();
			 stm.close();
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
		}
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View frame = new View();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public View() {
		setTitle("Hệ thống Quản lý Bán vé tàu");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 840, 610);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(10, 11, 807, 199);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lbForm = new JLabel("Thông tin vé tàu");
		lbForm.setForeground(Color.LIGHT_GRAY);
		lbForm.setBounds(0, 0, 165, 19);
		panel.add(lbForm);
		lbForm.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JLabel lbMaCT = new JLabel("Mã vé tàu");
		lbMaCT.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbMaCT.setBounds(10, 32, 118, 24);
		panel.add(lbMaCT);
		
		tfMaCT = new JTextField();
		tfMaCT.setBounds(158, 32, 258, 24);
		panel.add(tfMaCT);
		tfMaCT.setColumns(10);
		
		JLabel lbTenHK = new JLabel("Họ tên hành khách");
		lbTenHK.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbTenHK.setBounds(10, 64, 138, 24);
		panel.add(lbTenHK);
		
		tfTenHK = new JTextField();
		tfTenHK.setColumns(10);
		tfTenHK.setBounds(158, 64, 258, 24);
		panel.add(tfTenHK);
		
		JLabel lbSDT = new JLabel("Số điện thoại");
		lbSDT.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbSDT.setBounds(10, 99, 138, 24);
		panel.add(lbSDT);
		
		tfSDT = new JTextField();
		tfSDT.setColumns(10);
		tfSDT.setBounds(158, 99, 258, 24);
		panel.add(tfSDT);
		
		tfChuyentau = new JTextField();
		tfChuyentau.setColumns(10);
		tfChuyentau.setBounds(158, 133, 258, 24);
		panel.add(tfChuyentau);
		
		JLabel lbChuyentau = new JLabel("Chuyến tàu");
		lbChuyentau.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbChuyentau.setBounds(10, 133, 138, 24);
		panel.add(lbChuyentau);
		
		JLabel lbNgaydi = new JLabel("Ngày khởi hành");
		lbNgaydi.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbNgaydi.setBounds(466, 32, 118, 24);
		panel.add(lbNgaydi);
		
		tfNgaydi = new JTextField();
		tfNgaydi.setColumns(10);
		tfNgaydi.setBounds(596, 32, 142, 24);
		panel.add(tfNgaydi);
		
		JLabel lbSoghe = new JLabel("Số ghế");
		lbSoghe.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbSoghe.setBounds(466, 64, 108, 24);
		panel.add(lbSoghe);
		
		tfSoghe = new JTextField();
		tfSoghe.setColumns(10);
		tfSoghe.setBounds(596, 64, 142, 24);
		panel.add(tfSoghe);
		
		JLabel lbToa = new JLabel("Toa");
		lbToa.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbToa.setBounds(466, 99, 60, 24);
		panel.add(lbToa);
		
		String toa[]= {"1","2","3"};
		cbToa = new JComboBox();
		cbToa.setModel(new DefaultComboBoxModel(toa));
		cbToa.setForeground(new Color(0, 0, 0));
		cbToa.addItem("");
		cbToa.setBounds(596, 99, 142, 24);
		panel.add(cbToa);
		
		JLabel lbTinhtrang = new JLabel("Tình trạng");
		lbTinhtrang.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbTinhtrang.setBounds(466, 133, 108, 24);
		panel.add(lbTinhtrang);
		
		tfTinhtrang = new JTextField();
		tfTinhtrang.setColumns(10);
		tfTinhtrang.setBounds(596, 133, 142, 24);
		panel.add(tfTinhtrang);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(218, 278, 604, 2);
		contentPane.add(separator);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Mã vé", "Tên hành khách", "Số điện thoại", "Chuyến tàu", "Ngày khởi hành", "Số ghế", "Toa", "Tình trạng"
			}
		));
		getData();
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 294, 807, 268);
		contentPane.add(scrollPane);

		JLabel lbIntro = new JLabel("Danh sách vé tàu đã đăng kí");
		lbIntro.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbIntro.setBounds(10, 263, 224, 26);
		contentPane.add(lbIntro);
		
		JButton btnThem = new JButton("Thêm");
		btnThem.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnThem.setBounds(37, 221, 101, 31);
		contentPane.add(btnThem);
		btnThem.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				btnThem(e);
			}
		});
		
		JButton btnXoa = new JButton("Xoá");
		btnXoa.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnXoa.setBounds(149, 221, 101, 31);
		btnXoa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				RecordTable = (DefaultTableModel)table.getModel();
				int SelectedRows = table.getSelectedRow();
			}
		});
		contentPane.add(btnXoa);
		btnXoa.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				btnXoa(e);
				getData();
			}
		});
		
		JButton btnSua= new JButton("Sửa");
		btnSua.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				RecordTable = (DefaultTableModel)table.getModel();
				int SelectedRows = table.getSelectedRow();
				
			}
		});
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSua(e);
				getData();
			}
		});
		btnSua.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSua.setBounds(263, 221, 101, 31);
		contentPane.add(btnSua);
//		btnTim.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				btnTim(e);
//			}
//		});
		
		JLabel lblTim = new JLabel("Tìm kiếm qua mã vé");
		lblTim.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTim.setBounds(393, 227, 136, 31);
		contentPane.add(lblTim);
		
		tfTim = new JTextField();
		tfTim.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				tfTim(e);
			}
		});
		tfTim.setBounds(531, 227, 271, 31);
		contentPane.add(tfTim);
		tfTim.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.setBounds(373, 218, 444, 49);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
	}
	
	private void btnThem(ActionEvent e) {
		if(tfMaCT.getText().length()==0  || tfTenHK.getText().length()==0 || tfSDT.getText().length()==0 || 
				tfChuyentau.getText().length()==0  || tfNgaydi.getText().length()==0 || tfSoghe.getText().length()==0 ||
				tfTinhtrang.getText().length()==0)
		{
			JOptionPane.showMessageDialog(null, "Các thông tin không được để trống!", "Thông báo",
                    JOptionPane.WARNING_MESSAGE);
		} 
		else {
			try {
				Connection conn = Database.connectDB();
	        	String sql="INSERT INTO chuyentau(mave,tenkhach,sdt,chuyentau,ngaykhoihanh,soghe,toa,tinhtrang) VALUES (?,?,?,?,?,?,?,?) ";
	   
	        	PreparedStatement pstm = conn.prepareStatement(sql);
	    		pstm.setString(1, tfMaCT.getText());
	    		pstm.setString(2, tfTenHK.getText());
	    		pstm.setString(3, tfSDT.getText());
	    		pstm.setString(4, tfChuyentau.getText());
	    		pstm.setString(5, tfNgaydi.getText());
	    		pstm.setString(6, tfSoghe.getText());
	    		pstm.setString(7, cbToa.getSelectedItem().toString());
	    		pstm.setString(8, tfTinhtrang.getText());

	    		if (JOptionPane.showConfirmDialog(this, "Xác nhận vé?","Xác nhận",
						JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
	    			pstm.executeUpdate();
	    			pstm.close();
		    		conn.close();
		    		JOptionPane.showMessageDialog(null, "Thêm vé tàu thành công!", "Thông báo",
		                    JOptionPane.INFORMATION_MESSAGE);
		    		reset();
		    		getData();
				}
			} catch (Exception e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "Lỗi!", "Thông báo",
	                    JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	private void btnXoa(ActionEvent e) {
		try {
    		Connection conn = Database.connectDB();
			PreparedStatement pstm = conn.prepareStatement("delete from chuyentau where mave = ?");
			pstm.setString(1, table.getValueAt(table.getSelectedRow(), 0).toString());
			if (JOptionPane.showConfirmDialog(this, "Xoá Thông tin Vé này?","Xác nhận",
					JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
				pstm.execute();
				pstm.close();
	    		conn.close();
			}
			JOptionPane.showMessageDialog(null, "Xoá thành công!", "Thông báo",
                    JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, "Lỗi!", "Thông báo",
            JOptionPane.ERROR_MESSAGE);
		}		
	}
	
	private void btnSua(ActionEvent e) {
		try {
    		Connection conn = Database.connectDB();
			PreparedStatement pstm = conn.prepareStatement("UPDATE chuyentau SET tenkhach=?,sdt=?,chuyentau=?,ngaykhoihanh=?,soghe=?,toa=?,tinhtrang=? WHERE mave=?");

			pstm.setString(8, tfMaCT.getText());
    		pstm.setString(1, tfTenHK.getText());
    		pstm.setString(2, tfSDT.getText());
    		pstm.setString(3, tfChuyentau.getText());
    		pstm.setString(4, tfNgaydi.getText());
    		pstm.setString(5, tfSoghe.getText());
    		pstm.setString(6, cbToa.getSelectedItem().toString());
    		pstm.setString(7, tfTinhtrang.getText());
			
			if (JOptionPane.showConfirmDialog(this, "Xác nhận thay đổi thông tin vé?","Xác nhận",
					JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
    			pstm.execute();
    			pstm.close();
	    		conn.close();
	    		JOptionPane.showMessageDialog(null, "Thay đổi thông tin vé tàu thành công!", "Thông báo",
	                    JOptionPane.INFORMATION_MESSAGE);
	    		reset();
			}
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, "Lỗi!", "Thông báo",
            JOptionPane.ERROR_MESSAGE);
		}		
	}
	private void tfTim(KeyEvent e) {
		try {
    		Connection conn = Database.connectDB();
    		String tim = "%"+tfTim.getText() +"%";
		Statement stm=conn.createStatement();
		ResultSet rst=stm.executeQuery("select * from chuyentau WHERE mave LIKE '"+tim+"'");
		ResultSetMetaData rsmData=rst.getMetaData();
		int col = rsmData.getColumnCount();

		RecordTable = (DefaultTableModel)table.getModel();
		RecordTable.setRowCount(0);
		
		while (rst.next()) {
			   data = new Vector();
			   for (int i=1;i<=col;i++) {
			   data.add(rst.getString("mave"));
			   data.add(rst.getString("tenkhach"));
			   data.add(rst.getString("sdt"));
			   data.add(rst.getString("chuyentau"));
			   data.add(rst.getDate("ngaykhoihanh"));
			   data.add(rst.getInt("soghe"));
			   data.add(rst.getString("toa"));
			   data.add(rst.getString("tinhtrang"));
			   }
			   RecordTable.addRow(data);
			   table.getColumnModel().getColumn(0).setPreferredWidth(18);
			   table.getColumnModel().getColumn(5).setPreferredWidth(23);
			   table.getColumnModel().getColumn(6).setPreferredWidth(12);
			 }
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (table.getSelectedRow() >= 0) {
					tfMaCT.setText(table.getValueAt(table.getSelectedRow(),0)+"");
					tfTenHK.setText(table.getValueAt(table.getSelectedRow(),1)+"");
					tfSDT.setText(table.getValueAt(table.getSelectedRow(),2)+"");
					tfChuyentau.setText(table.getValueAt(table.getSelectedRow(),3)+"");
					tfNgaydi.setText(table.getValueAt(table.getSelectedRow(),4)+"");
					tfSoghe.setText(table.getValueAt(table.getSelectedRow(),5)+"");
					cbToa.setSelectedItem(table.getModel().getValueAt(table.getSelectedRow(),6)+"");
					tfTinhtrang.setText(table.getValueAt(table.getSelectedRow(),7)+"");
				}
			}
			
		});
		
		 conn.close();
		 rst.close();
		 stm.close();
	} catch (Exception ex) {
		System.out.println(ex.toString());
		ex.printStackTrace();
	}
}

	private void reset() {
		tfMaCT.setText("");
		tfTenHK.setText("");
		tfSDT.setText("");
		tfChuyentau.setText("");
		tfNgaydi.setText("");
		tfSoghe.setText("");
		cbToa.setSelectedIndex(0);
		tfTinhtrang.setText("");
	}
	
}
