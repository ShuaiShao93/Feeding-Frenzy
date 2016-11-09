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
		restart = new JButton("���¿�ʼ");    //�������¿�ʼ�ͽ���������ť�����趨��λ��
		end = new JButton("�˳�");
		restart.setBounds(300, 300, 200, 50);
		end.setBounds(300, 450, 200, 50);
		add(restart);
		add(end);
	}
	public void paintComponent(Graphics g){     //��дpaintComponent��������
		super.paintComponent(g);
		g.setColor(Color.ORANGE);
		g.setFont(new Font("�����п�",Font.BOLD,40));
		g.drawImage(bg, 0, 0, this.getWidth(), this.getHeight(), this);
		g.drawString("�������յȼ�Ϊ "+MainFrame.Player.getLevel()+" ����Ϊ "+ MainFrame.Player.getExp(),155, 80);
	}


}
