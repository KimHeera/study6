package connect;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Frame extends JFrame{
	JPanel homePanel = new JPanel();
	JLabel chack = new JLabel();
	JTextField inputStone = new JTextField();
	
	BadukPanel baduk;
	int BN=0;
	int x, y;
	
	JButton startbtn = new JButton();
	
	Frame(){
		homePanel.setLayout(null);
		homePanel.setBounds(0, 0, 1400, 900); //프레임 초기 사이즈
		homePanel.setBackground(Color.LIGHT_GRAY);
		homePanel.setVisible(true); //프레임이 보이도록
		this.add(homePanel);
		
		this.setLayout(null);
		this.setTitle("육목"); //타이틀
		this.setSize(1400, 1000); //프레임 초기 사이즈
		this.setLocationRelativeTo(null);
		this.setResizable(true); //프레임크기 조절 가능
		this.setVisible(true); //프레임이 보이도록
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //x버튼 활성
	}
	
	public void make() {
		baduk = new BadukPanel();
		
		startbtn.setText("start");
		startbtn.setFont(new Font("Arial", Font.BOLD, 15));
		startbtn.setBounds(100, 70, 150, 100);
		startbtn.addActionListener(clk);
		startbtn.setVisible(true);
		homePanel.add(startbtn);
		
		chack.setText("Stone 개수 : ");
		chack.setFont(new Font("Arial", Font.BOLD, 15));
		chack.setBounds(110, 270, 150, 50);
		chack.setVisible(true);
		homePanel.add(chack);
		

		homePanel.add(baduk.make());
		homePanel.add(baduk.makelabel());
		
		inputStone.setText("");
		inputStone.setFont(new Font("Arial", Font.BOLD, 15));
		inputStone.setBounds(190, 270, 50, 50);
		inputStone.setVisible(true);
		homePanel.add(inputStone);
	}
	
	ActionListener clk = new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		if(e.getActionCommand().equals("start")) {
    			if(Integer.parseInt(inputStone.getText())>=0 && Integer.parseInt(inputStone.getText())<=5) {
    				BN = Integer.parseInt(inputStone.getText());
    				System.out.println("착수금지점의 개수 : " + BN);
        			
        			baduk.setBN(BN);
    			}
    		}
    		
    	}
	};
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Frame frame = new Frame();
		frame.make();
	}

}
