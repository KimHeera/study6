package connect;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class BadukPanel extends JPanel{
	JLabel turn = new JLabel();
	int linewidth = 35;
	int BN;
	int x, y;
	int cnt=0;
	int label = 0;
	Color c;
	
	Ellipse2D.Double[][] ellipse = new Ellipse2D.Double[19][19];
	private int[][] dollColor = new int [19][19];
	
	ArrayList<BadukDoll> doll = new ArrayList<BadukDoll>();
	
	
	BadukPanel(){
		for(int i=0;i<19;i++) {
			for(int j=0;j<19;j++) {
				dollColor[i][j]=-1;
			}
		}
	}
	
	public JPanel make(){
		Color color = new Color(204, 153,000);
		this.setLayout(null);
		this.setBounds(350, 70, 700, 700); //프레임 초기 사이즈
		this.setBackground(color);
		this.setVisible(true); //프레임이 보이도록
		this.addMouseListener(new MyMouseListener());
		this.addMouseMotionListener(new MyMouseListener());
		return this;
	}
	
	public class BadukDoll{
		Color c;
		Ellipse2D fill;
		
		public BadukDoll(Color color, Ellipse2D fill){
			this.c = color;
			this.fill = fill;
		}
	}
	
	public JLabel makelabel() {
		turn.setText("");

		turn.setFont(new Font("Arial", Font.BOLD, 30));
		turn.setBounds(135, 170, 150, 100);
		turn.setVisible(true);
		
		return turn;
	}
		
	@Override
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		Graphics2D g2=(Graphics2D)g;
		
		for(int i = 0; i < 660 ; i+= linewidth) {
			g2.drawLine(i + 35, 35, i+35, 665);   
			g2.drawLine(35, i + 35, 665,  i + 35);   
		}
		
		for(int i = 18 ; i>=0 ; i--) {
			for(int j = 18 ; j >=0 ; j--) {
				ellipse[i][j] = new Ellipse2D.Double(i*630/18-25/2+linewidth, j*630/18-25/2+ linewidth, 25, 25);
				
				g2.setColor(Color.black);
				if(i!=0 && i%3==0 && j!=0 && j%3==0 && i%6!=0 && j%6!=0)
					g2.fill(new Ellipse2D.Double(i*630/18-5/2+linewidth, j*630/18-5/2+linewidth, 5, 5));
			}
		}
		
		
		for(BadukDoll c:doll) {
			g2.setColor(c.c);
			g2.fill(c.fill);
		}
		
		
	}
	
	public int checkFinishGo(int x, int y) {

		if(checkHorizontal(x, y, dollColor[x][y]) == 6) {
			return dollColor[x][y];
		}
		else if(checkVertical(x, y, dollColor[x][y]) == 6) {
			return dollColor[x][y];
		}
		else if(checkLRDiagonal(x, y, dollColor[x][y]) == 6) {
			return dollColor[x][y];
		}
		else if(checkRLDiagonal(x, y, dollColor[x][y]) == 6) {
			return dollColor[x][y];
		}

		return 0;
	}

	private int checkHorizontal(int row, int col, int rock) {
		try {
			if (dollColor[row][col] != rock)
				return 0;
			return checkLeft(row -1, col, rock) + 1 + checkRight(row+1, col, rock);
			
		} catch (ArrayIndexOutOfBoundsException e) {
			return 0;
		}
	}
	
	private int checkLeft(int row, int col, int rock) {
		try {
			if (dollColor[row][col] != rock)
				return 0;
			
			return 1 + checkLeft(row - 1, col, rock);
			
		} catch (ArrayIndexOutOfBoundsException e) {
			return 0;
		} 
	}
	
	private int checkRight(int row, int col, int rock) {
		try {
			if (dollColor[row][col] != rock)
				return 0;
			return 1 + checkRight(row + 1, col, rock);
			
		} catch (ArrayIndexOutOfBoundsException e) {
			return 0;
		}
	}
	
	private int checkVertical(int row, int col, int rock) {
		try {
			if (dollColor[row][col] != rock)
				return 0;
			return checkUp(row, col - 1, rock) + 1 + checkDown(row, col + 1, rock);
			
		} catch (ArrayIndexOutOfBoundsException e) {
			return 0;
		}
	}
	
	private int checkUp(int row, int col, int rock) {
		try {
			if (dollColor[row][col] != rock)
				return 0;
			return 1 + checkUp(row, col - 1, rock);
			
		} catch (ArrayIndexOutOfBoundsException e) {
			return 0;
		}
	}
	
	private int checkDown(int row, int col, int rock) {
		try {
			if (dollColor[row][col] != rock)
				return 0;
			return 1 + checkDown(row, col + 1, rock);
			
		} catch (ArrayIndexOutOfBoundsException e) {
			return 0;
		}
	}
	
	private int checkLRDiagonal(int row, int col, int rock) {
		try {
			if (dollColor[row][col] != rock)
				return 0;
			return checkLRDiagonalUp(row - 1, col - 1, rock) + 1 + checkLRDiagonalDown(row + 1, col + 1, rock);
			
		} catch (ArrayIndexOutOfBoundsException e) {
			return 0;
		}
	}
	
	private int checkLRDiagonalUp(int row, int col, int rock) {
		try {
			if (dollColor[row][col] != rock)
				return 0;
			return 1 + checkLRDiagonalUp(row - 1, col - 1, rock);
			
		} catch (ArrayIndexOutOfBoundsException e) {
			return 0;
		}
	}
	
	private int checkLRDiagonalDown(int row, int col, int rock) {
		try {
			if (dollColor[row][col] != rock)
				return 0;
			return 1 + checkLRDiagonalDown(row + 1, col + 1, rock);
			
		} catch (ArrayIndexOutOfBoundsException e) {
			return 0;
		}
	}

	private int checkRLDiagonal(int row, int col, int rock) {
		try {
			if (dollColor[row][col] != rock)
				return 0;
			return checkRLDiagonalUp(row + 1, col - 1, rock) + 1 + checkRLDiagonalDown(row - 1, col + 1, rock);
			
		} catch (ArrayIndexOutOfBoundsException e) {
			return 0;
		}
	}
	
	private int checkRLDiagonalUp(int row, int col, int rock) {
		try {
			if (dollColor[row][col] != rock)
				return 0;
			return 1 + checkRLDiagonalUp(row + 1, col - 1, rock);
			
		} catch (ArrayIndexOutOfBoundsException e) {
			return 0;
		}
	}
	
	private int checkRLDiagonalDown(int row, int col, int rock) {
		try {
			if (dollColor[row][col] != rock)
				return 0;
			return 1 + checkRLDiagonalDown(row - 1, col + 1, rock);
			
		} catch (ArrayIndexOutOfBoundsException e) {
			return 0;
		}
	}
	
	
	
	
	public class MyMouseListener extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			
		}
		@Override
		public void mouseClicked(MouseEvent e) {
			for(int i=0; i<19; i++) {
				for(int j=0; j<19; j++) {
					if(ellipse[i][j].contains(e.getPoint())){
						for(BadukDoll c:doll){ //중복 확인
			                if (c.fill.contains(e.getPoint())) {
			                   return;
			                }
			             }
						if(BN > doll.size()) {
							c = Color.RED;
							dollColor[i][j] = 3;
						}
						else {
							if(cnt==0) {
								c = Color.BLACK;
								cnt++;
								dollColor[i][j] = 1;
							}
							else {
								if(cnt/2%2==1) {
									c = Color.WHITE;
									dollColor[i][j] = 2;
								}
								else {
									c = Color.BLACK;
									dollColor[i][j] = 1;
								}
							}
							cnt++;
						}
						
						if (doll.size() < BN) turn.setText("못누르능거");
						else if (doll.size() - BN == 0) turn.setText("힁거");
						else if (doll.size() - BN == 1) turn.setText("거멍거");
						else if (((doll.size() + 1 - BN )/ 2) % 2 == 0) turn.setText("힁거");
						else turn.setText("거멍거");
						
						doll.add(new BadukDoll(c, ellipse[i][j]));
						
						int result = checkFinishGo(i, j);
						
						if(result == 1) {
							System.out.print("검정색 이겨따 야호");
							JOptionPane.showMessageDialog(null, "흑돌이 이겼습니다!", "게임이 끝났습니다.", JOptionPane.PLAIN_MESSAGE);
							doll.clear();
							for(int y=0;i<19;i++) {
								for(int k=0;j<19;j++) {
									dollColor[y][k]=-1;
								}
							}
							cnt=0;
						}
						
						else if(result == 2) {
							System.out.print("흰색 이겨따 야호");
							JOptionPane.showMessageDialog(null, "백돌이 이겼습니다!", "게임이 끝났습니다.", JOptionPane.PLAIN_MESSAGE);
							doll.clear();
							for(int y=0;i<19;i++) {
								for(int k=0;j<19;j++) {
									dollColor[y][k]=-1;
								}
							}
							cnt=0;
						}
						break;
					}
				}
			}
			BadukPanel.this.repaint();
			
			
		} //클릭할때
		
		@Override
		public void mouseReleased(MouseEvent e) {
			
		}
		@Override
		public void mouseEntered(MouseEvent e) {
		}
		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
		}
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
		}
	}
	
	

	
	public void setBN(int BN) {
		this.BN =BN;
	}
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int setM() {
		return label;
	}

}
