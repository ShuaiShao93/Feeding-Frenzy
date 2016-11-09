import java.awt.*;
import javax.swing.*;
public class StartPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Image bg;
	JButton start,end;

	public StartPanel() {
		// TODO Auto-generated constructor stub
		this.setBounds(0,0,MainFrame.Framewidth,MainFrame.Frameheight);
		this.setLayout(null);
		ImageIcon tmp = new ImageIcon(this.getClass().getResource("image/startbg.jpg"));		
		bg = tmp.getImage();
		start = new JButton("��ʼ��Ϸ");    //���ӿ�ʼ�ͽ���������ť�����趨��λ��
		end = new JButton("�˳�");
		start.setBounds(300, 300, 200, 50);
		end.setBounds(300, 450, 200, 50);
		add(start);
		add(end);
	}
	public void paintComponent(Graphics g){     //��дpaintComponent��������
		super.paintComponent(g);
		g.drawImage(bg, 0, 0, this.getWidth(), this.getHeight(), this);
	}

}
