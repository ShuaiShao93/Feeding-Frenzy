import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;
public class scene_1 extends JPanel implements Runnable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Image background1;
	private final static int width1=1540;     //�����ߴ�
	private final static int height1=979;
	private static boolean run1=false;         //�߳�״̬
	private boolean up,down,left,right;     //�ƶ�״̬
	private npc1 npc; //�ó����������������״̬
	public scene_1() {
		// TODO Auto-generated constructor stub
		up=false;
		down=false;
		left=false;
		right=false;
		this.setBounds(0,0,MainFrame.Framewidth,MainFrame.Frameheight);
		ImageIcon tmp = new ImageIcon(this.getClass().getResource("image/bg1.jpg"));
		background1 = tmp.getImage();
		//��ʼλ��x����ߣ�y�ڳ���������
		this.setFocusable(true);         //����õ��������ʹ��������Ч
		npc=new npc1();
		this.addKeyListener(new KeyAdapter(){   //ͨ���ڲ������Ӽ��̼������������������Ұ�ť��press��release��ʹ��Up,Down,Left,Right�ĸ����������ı�
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode()==KeyEvent.VK_UP)
	    			setUp(true);
	    		if(e.getKeyCode()==KeyEvent.VK_DOWN)
	    			setDown(true);
	    		if(e.getKeyCode()==KeyEvent.VK_LEFT)
	    			{setLeft(true);
	    			 MainFrame.Player.setDir(0);
	    			}
	    		if(e.getKeyCode()==KeyEvent.VK_RIGHT)
	    			{setRight(true);
	    			 MainFrame.Player.setDir(1);
	    			}
	    		if(e.getKeyCode()==KeyEvent.VK_ESCAPE)   //����esc,����������PausePanel����
	    		    {MainFrame.PP.setVisible(true);
	    			setVisible(false);
	    		    }
	    		if(e.getKeyCode()==KeyEvent.VK_S)
	    			MainFrame.setMiji(!MainFrame.isMiji());     //��s���������
			}
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode()==KeyEvent.VK_UP)
	    			setUp(false);
	    		if(e.getKeyCode()==KeyEvent.VK_DOWN)
	    			setDown(false);
	    		if(e.getKeyCode()==KeyEvent.VK_LEFT)
	    			setLeft(false);
	    		if(e.getKeyCode()==KeyEvent.VK_RIGHT)
	    			setRight(false);
			}
	    });
	}
	public void start(){  
		//�̵߳�����������run����
		run1=true;
		Thread thread1=new Thread(this);
		thread1.start();
	}
	public void stop(){ 
		//���ó�������������ô˷���ֹͣ�߳�
		run1=false;
	}
	public void update(Graphics g){   
		 //��дupdate��ֹ������˸
		paint(g);
	}
	public void paint(Graphics g){    
		//��д�����paint����ʾ����
		int x=MainFrame.Player.getX()+MainFrame.Player.getWid()/2;
		int y=MainFrame.Player.getY()+MainFrame.Player.getHei()/2;
		int Fw=this.getWidth();
		int Fh=this.getHeight();
		//�ж��Ƿ���봫����
		if(x>1399&&x<1445&&y>710&&y<753){
			MainFrame.toScene2(1);
		}
		Image offscreen=createImage(getWidth1(),getHeight1());       
		//����˫�ػ��壬����offscreen��Ϊ����ͼ������g1��offscreen�ϻ������ú��ٻ�����Ļ�ϡ�ע��offscreen��һ���ܴ��ͼƬ����ʱ����뻭����Ļ��������Ͻ�Ȼ�����ض��Ĳ�����ʾ����Ļ��
		Graphics g1=offscreen.getGraphics();
		g1.drawImage(background1, 0, 0, null);
		g1.drawImage(MainFrame.Player.getRole(),MainFrame.Player.getX(),MainFrame.Player.getY(),null);
		npc.change();             
		//ÿ�λ�npcʱ��ִ��change��������λ��
		for(int i=0;i<npc.NPCNUM;i++){      
			g1.drawImage(npc.getRole(i),npc.getX(i),npc.getY(i),null);			
		}
		if(x>=Fw/2&&x<=getWidth1()-Fw/2&&y>=Fh/2&&y<=getHeight1()-Fh/2)   
			//ͨ���ж�player����λ����ȷ����ʾ����Ļ�ϵĻ�������������ͼ�ϵľ������꣬�����ڱ�Ե������playerΪ���ĵľ��Σ�Ȼ���offscreen������Ļ��������Ͻ�
			g.drawImage(offscreen, Fw/2-x, Fh/2-y,null);
		else if(x<Fw/2&&y<Fh/2)           
			//�������»����ұ�Ե���ĸ��ǣ���ôֻ����Ӧλ�õľ��Σ���ͬ
			g.drawImage(offscreen, 0, 0,null);
		else if(x<Fw/2&&y>=Fh/2&&y<=getHeight1()-Fh/2)
			g.drawImage(offscreen, 0, Fh/2-y,null);
		else if(x<Fw/2&&y>getHeight1()-Fh/2)
			g.drawImage(offscreen, 0, Fh-getHeight1(),null);
		else if(x>=Fw/2&&x<=getWidth1()-Fw/2&&y<Fh/2)
			g.drawImage(offscreen, Fw/2-x, 0,null);
		else if(x>=Fw/2&&x<=getWidth1()-Fw/2&&y>getHeight1()-Fh/2)
			g.drawImage(offscreen, Fw/2-x, Fh-getHeight1(),null);
		else if(x>getWidth1()-Fw/2&&y<Fh/2)
		    g.drawImage(offscreen, Fw-getWidth1(), 0,null);
		else if(x>getWidth1()-Fw/2&&y>=Fh/2&&y<=getHeight1()-Fh/2)
			g.drawImage(offscreen, Fw-getWidth1(), Fh/2-y,null);
		else if(x>getWidth1()-Fw/2&&y>getHeight1()-Fh/2)
		    g.drawImage(offscreen, Fw-getWidth1(), Fh-getHeight1(),null);
		g.setColor(Color.RED);       //ֱ���ڻ��涥��ʾ�ȼ�
		g.setFont(new Font("�����п�",Font.BOLD,40));
		g.drawString("�ȼ�: "+MainFrame.Player.getLevel(),120, 50);  
		g.setColor(Color.ORANGE);
		g.drawString("����: ",300, 50);           //�ڵȼ�������һ������������Ϊ�õȼ�������ʱ�ľ��飬����Ϊ300���������α�ʾ��ǰ����
		int l = (int) (MainFrame.Player.getExp()/(100.0*MainFrame.Player.getLevel())*300);  //lΪ���㵱ǰ��������Ӧ�ĳ��� 
		g.drawRect(420, 20, 300, 40);
		g.fillRect(420, 20, l, 40);
		g.drawString(" " +MainFrame.Player.getExp(), 420+l, 50);
	}
	public void run(){            
		//�߳�run�м���Լ����������Ұ���״̬���жϣ��Ӷ��ı����꣬ͨ��sleep��ʹ��ÿ��30����ִ��һ�Σ�������repaint�Ի�������ػ�
		while(run1){
			if(isUp())
				MainFrame.Player.setY(MainFrame.Player.getY()-MainFrame.Player.pspeed);
			if(isDown())
				MainFrame.Player.setY(MainFrame.Player.getY()+MainFrame.Player.pspeed);
			if(isLeft())
				MainFrame.Player.setX(MainFrame.Player.getX()-MainFrame.Player.pspeed);
			if(isRight())
				MainFrame.Player.setX(MainFrame.Player.getX()+MainFrame.Player.pspeed);
			repaint();
			try{
				Thread.sleep(30);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}
	/**
	 * @return the up
	 */
	public boolean isUp() {
		return up;
	}
	/**
	 * @param up the up to set
	 */
	public void setUp(boolean up) {
		this.up = up;
	}
	/**
	 * @return the down
	 */
	public boolean isDown() {
		return down;
	}
	/**
	 * @param down the down to set
	 */
	public void setDown(boolean down) {
		this.down = down;
	}
	/**
	 * @return the left
	 */
	public boolean isLeft() {
		return left;
	}
	/**
	 * @param left the left to set
	 */
	public void setLeft(boolean left) {
		this.left = left;
	}
	/**
	 * @return the right
	 */
	public boolean isRight() {
		return right;
	}
	/**
	 * @param right the right to set
	 */
	public void setRight(boolean right) {
		this.right = right;
	}
	/**
	 * @return the width1
	 */
	public static int getWidth1() {
		return width1;
	}
	/**
	 * @return the height1
	 */
	public static int getHeight1() {
		return height1;
	}
}
