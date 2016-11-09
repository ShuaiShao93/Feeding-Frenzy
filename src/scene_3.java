import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class scene_3 extends JPanel implements Runnable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Image background3;
	private final static int width3=1536;     //�����ߴ�
	private final static int height3=981;
	private static boolean run3=false;         //�߳�״̬
	private boolean up,down,left,right;     //�ƶ�״̬
	private npc3 npc; //�ó����������������״̬

	public scene_3() {
		// TODO Auto-generated constructor stub
		up=false;
		down=false;
		left=false;
		right=false;
		this.setBounds(0,0,MainFrame.Framewidth,MainFrame.Frameheight);
		ImageIcon tmp = new ImageIcon(this.getClass().getResource("image/bg3.jpg"));
		background3 = tmp.getImage(); 
		//��ʼλ��x����ߣ�y�ڳ���������
		this.setFocusable(true);         //����õ��������ʹ��������Ч
		npc=new npc3();
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
		run3=true;
		Thread thread3=new Thread(this);
		thread3.start();
	}
	public void stop(){ 
		//���ó�������������ô˷���ֹͣ�߳�
		run3=false;
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
		if(x>91&&x<148&&y>62&&y<121){
			stop();   //��ֹ�߳�
			setVisible(false);
			MainFrame.toScene2(3);
		}
		Image offscreen=createImage(getWidth3(),getHeight3());       
		//����˫�ػ��壬����offscreen��Ϊ����ͼ������g1��offscreen�ϻ������ú��ٻ�����Ļ�ϡ�ע��offscreen��һ���ܴ��ͼƬ����ʱ����뻭����Ļ��������Ͻ�Ȼ�����ض��Ĳ�����ʾ����Ļ��
		Graphics g1=offscreen.getGraphics();
		g1.drawImage(background3, 0, 0, null);
		g1.drawImage(MainFrame.Player.getRole(),MainFrame.Player.getX(),MainFrame.Player.getY(),null);
		npc.change();             
		//ÿ�λ�npcʱ��ִ��change��������λ��
		for(int i=0;i<npc.NPCNUM;i++){      
			g1.drawImage(npc.getRole(i),npc.getX(i),npc.getY(i),null);			
		}
		if(x>=Fw/2&&x<=getWidth3()-Fw/2&&y>=Fh/2&&y<=getHeight3()-Fh/2)   
			//ͨ���ж�player����λ����ȷ����ʾ����Ļ�ϵĻ�������������ͼ�ϵľ������꣬�����ڱ�Ե������playerΪ���ĵľ��Σ�Ȼ���offscreen������Ļ��������Ͻ�
			g.drawImage(offscreen, Fw/2-x, Fh/2-y,null);
		else if(x<Fw/2&&y<Fh/2)           
			//�������»����ұ�Ե���ĸ��ǣ���ôֻ����Ӧλ�õľ��Σ���ͬ
			g.drawImage(offscreen, 0, 0,null);
		else if(x<Fw/2&&y>=Fh/2&&y<=getHeight3()-Fh/2)
			g.drawImage(offscreen, 0, Fh/2-y,null);
		else if(x<Fw/2&&y>getHeight3()-Fh/2)
			g.drawImage(offscreen, 0, Fh-getHeight3(),null);
		else if(x>=Fw/2&&x<=getWidth3()-Fw/2&&y<Fh/2)
			g.drawImage(offscreen, Fw/2-x, 0,null);
		else if(x>=Fw/2&&x<=getWidth3()-Fw/2&&y>getHeight3()-Fh/2)
			g.drawImage(offscreen, Fw/2-x, Fh-getHeight3(),null);
		else if(x>getWidth3()-Fw/2&&y<Fh/2)
		    g.drawImage(offscreen, Fw-getWidth3(), 0,null);
		else if(x>getWidth3()-Fw/2&&y>=Fh/2&&y<=getHeight3()-Fh/2)
			g.drawImage(offscreen, Fw-getWidth3(), Fh/2-y,null);
		else if(x>getWidth3()-Fw/2&&y>getHeight3()-Fh/2)
		    g.drawImage(offscreen, Fw-getWidth3(), Fh-getHeight3(),null);
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
		while(run3){
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
	 * @return the width3
	 */
	public static int getWidth3() {
		return width3;
	}
	/**
	 * @return the height3
	 */
	public static int getHeight3() {
		return height3;
	}
}