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
	private final static int width1=1540;     //背景尺寸
	private final static int height1=979;
	private static boolean run1=false;         //线程状态
	private boolean up,down,left,right;     //移动状态
	private npc1 npc; //该场景内其他鱼的资料状态
	public scene_1() {
		// TODO Auto-generated constructor stub
		up=false;
		down=false;
		left=false;
		right=false;
		this.setBounds(0,0,MainFrame.Framewidth,MainFrame.Frameheight);
		ImageIcon tmp = new ImageIcon(this.getClass().getResource("image/bg1.jpg"));
		background1 = tmp.getImage();
		//初始位置x在左边，y在场景的中央
		this.setFocusable(true);         //必须得到焦点才能使监听器生效
		npc=new npc1();
		this.addKeyListener(new KeyAdapter(){   //通过内部类增加键盘监听器，监听上下左右按钮的press和release来使得Up,Down,Left,Right四个变量发生改变
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
	    		if(e.getKeyCode()==KeyEvent.VK_ESCAPE)   //监听esc,监听到则让PausePanel出现
	    		    {MainFrame.PP.setVisible(true);
	    			setVisible(false);
	    		    }
	    		if(e.getKeyCode()==KeyEvent.VK_S)
	    			MainFrame.setMiji(!MainFrame.isMiji());     //按s键开关外挂
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
		//线程的启动，运行run（）
		run1=true;
		Thread thread1=new Thread(this);
		thread1.start();
	}
	public void stop(){ 
		//若该场景结束，则调用此方法停止线程
		run1=false;
	}
	public void update(Graphics g){   
		 //重写update防止画面闪烁
		paint(g);
	}
	public void paint(Graphics g){    
		//重写画板的paint来显示画面
		int x=MainFrame.Player.getX()+MainFrame.Player.getWid()/2;
		int y=MainFrame.Player.getY()+MainFrame.Player.getHei()/2;
		int Fw=this.getWidth();
		int Fh=this.getHeight();
		//判断是否进入传送门
		if(x>1399&&x<1445&&y>710&&y<753){
			MainFrame.toScene2(1);
		}
		Image offscreen=createImage(getWidth1(),getHeight1());       
		//利用双重缓冲，创建offscreen作为缓冲图像，先用g1在offscreen上画，画好后再画到屏幕上。注意offscreen是一个很大的图片，到时候必须画在屏幕外面的左上角然后让特定的部分显示在屏幕上
		Graphics g1=offscreen.getGraphics();
		g1.drawImage(background1, 0, 0, null);
		g1.drawImage(MainFrame.Player.getRole(),MainFrame.Player.getX(),MainFrame.Player.getY(),null);
		npc.change();             
		//每次画npc时先执行change方法更新位置
		for(int i=0;i<npc.NPCNUM;i++){      
			g1.drawImage(npc.getRole(i),npc.getX(i),npc.getY(i),null);			
		}
		if(x>=Fw/2&&x<=getWidth1()-Fw/2&&y>=Fh/2&&y<=getHeight1()-Fh/2)   
			//通过判断player所在位置来确定显示在屏幕上的画面在整个背景图上的具体坐标，若不在边缘，则画以player为中心的矩形，然后把offscreen画到屏幕外面的左上角
			g.drawImage(offscreen, Fw/2-x, Fh/2-y,null);
		else if(x<Fw/2&&y<Fh/2)           
			//若在上下或左右边缘或四个角，那么只画相应位置的矩形，下同
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
		g.setColor(Color.RED);       //直接在画面顶显示等级
		g.setFont(new Font("华文行楷",Font.BOLD,40));
		g.drawString("等级: "+MainFrame.Player.getLevel(),120, 50);  
		g.setColor(Color.ORANGE);
		g.drawString("经验: ",300, 50);           //在等级后面用一条非填充矩形作为该等级满经验时的经验，长度为300，用填充矩形表示当前经验
		int l = (int) (MainFrame.Player.getExp()/(100.0*MainFrame.Player.getLevel())*300);  //l为计算当前经验所对应的长度 
		g.drawRect(420, 20, 300, 40);
		g.fillRect(420, 20, l, 40);
		g.drawString(" " +MainFrame.Player.getExp(), 420+l, 50);
	}
	public void run(){            
		//线程run中加入对键盘上下左右按下状态的判断，从而改变坐标，通过sleep来使得每隔30毫秒执行一次，并调用repaint对画面进行重画
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
