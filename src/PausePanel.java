import java.awt.*;

import javax.swing.*;


public class PausePanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Image bg;
	JButton start,end;

	public PausePanel() {
		// TODO Auto-generated constructor stub
		this.setBounds(0,0,MainFrame.Framewidth,MainFrame.Frameheight);
		this.setLayout(null);
		ImageIcon tmp = new ImageIcon(this.getClass().getResource("image/startbg.jpg"));
		bg = tmp.getImage();
		start = new JButton("继续游戏");    //增加继续和结束两个按钮，并设定其位置
		end = new JButton("退出");
		start.setBounds(300, 300, 200, 50);
		end.setBounds(300, 450, 200, 50);
		add(start);
		add(end);
	}
	public void paintComponent(Graphics g){     //重写paintComponent来画背景
		super.paintComponent(g);
		g.setColor(Color.ORANGE);
		g.setFont(new Font("华文行楷",Font.BOLD,40));
		g.drawImage(bg, 0, 0, this.getWidth(), this.getHeight(), this);
		g.drawString("您的最终等级为 "+MainFrame.Player.getLevel()+" 经验为 "+ MainFrame.Player.getExp(),155, 80);
	}

}
