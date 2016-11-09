import java.awt.*;

import javax.swing.*;
public class EndPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Image bg;
	JButton restart,end;

	public EndPanel() {
		// TODO Auto-generated constructor stub
		this.setBounds(0,0,MainFrame.Framewidth,MainFrame.Frameheight);
		this.setLayout(null);
		ImageIcon tmp = new ImageIcon(this.getClass().getResource("image/endbg.jpg"));
		bg = tmp.getImage();
		restart = new JButton("重新开始");    //增加重新开始和结束两个按钮，并设定其位置
		end = new JButton("退出");
		restart.setBounds(300, 300, 200, 50);
		end.setBounds(300, 450, 200, 50);
		add(restart);
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
