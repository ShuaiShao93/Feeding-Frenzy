import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
public class MainFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static final int Framewidth=800;      //中心游戏画面的大小
	static final int Frameheight=600;
	static player Player;
	static int scene = 1;   //记录当前场景
	static scene_1 scene1;
	static scene_2 scene2;
	static scene_3 scene3;
	static StartPanel SP;
	static PausePanel PP;
	static EndPanel EP;
	private static boolean miji = false;   //给该游戏添加秘籍，按s键即可开关外挂，打开后可以吃任何鱼
	public MainFrame(){
		this.setSize(Framewidth+5,Frameheight+20);   //留出边缘
		this.setLayout(null);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLocationRelativeTo(null);
	    this.setResizable(false);
	    this.setVisible(true);
	    SP = new StartPanel();         //打开开始菜单
	    PP = new PausePanel();
	    EP = new EndPanel();
	    this.getContentPane().add(SP);
	    this.getContentPane().add(PP);
	    this.getContentPane().add(EP);
	    SP.setVisible(true);
	    PP.setVisible(false);
	    EP.setVisible(false);
        SP.start.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){    //给StartPanel的start按钮增加监听器来创建场景一
				Player=new player();
				Player.setX(MainFrame.Framewidth/2);
				Player.setY(979/2);   
				scene1=new scene_1();     //建立场景1
				getContentPane().add(scene1);
				scene1.start();  //场景一线程启动
				scene1.setVisible(true);
				SP.setVisible(false);
			}
		});
        SP.end.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){    //给StartPanel的end按钮增加监听器来结束游戏
				System.exit(0); 
			}
		});
        PP.start.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){    //给PausePanel的start按钮增加监听器来创建场景一
				if(scene==1)
					scene1.setVisible(true);
				else
					if(scene==2)
						scene2.setVisible(true);
			    else
					if(scene==3)
						scene3.setVisible(true);
				PP.setVisible(false);
			}
		});
        PP.end.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){    //给PausePanel的end按钮增加监听器来结束游戏
				System.exit(0); 
			}
		});
        EP.restart.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){    //给EndPanel的restart按钮增加监听器来创建场景一
				Player=new player();
				Player.setX(MainFrame.Framewidth/2);
				Player.setY(979/2);   
				scene1=new scene_1();     //建立场景1
				getContentPane().add(scene1);
				scene1.start();  //场景一线程启动
				scene1.setVisible(true);
				EP.setVisible(false);
			}
		});
        EP.end.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){    //给EndPanel的end按钮增加监听器来结束游戏
				System.exit(0); 
			}
		});
        
	}
	//从场景中player到达传送门时可以调用以下方法来改变场景
	static void toScene1(int src){   
	    if(src == 2){
	    	scene2.stop();
		    scene2.setVisible(false);
			Player.setX(1350);
			Player.setY(770);
		   }
	       scene = 1;
		   scene1 = new scene_1();
		   MainGame.MF.getContentPane().add(scene1);
		   scene1.setVisible(true);
		   scene1.start();
		   scene1.requestFocus();       //得到焦点使监听器生效
	   }
   static void toScene2(int src){
	   if(src == 1){
		   scene1.stop();   //终止线程
		   scene1.setVisible(false);
		   Player.setX(1280);
		   Player.setY(240);
	   }
	   if(src == 3){
		   scene3.stop();
		   scene2.setVisible(false);
		   Player.setX(1256);
		   Player.setY(830);
	   }
       scene = 2;
	   scene2 = new scene_2();
	   MainGame.MF.getContentPane().add(scene2);
	   scene2.setVisible(true);
	   scene2.start();
	   scene2.requestFocus();
   }
   static void toScene3(int src){   
	    if(src == 2){
	        scene2.stop();   //终止线程
	        scene2.setVisible(false);
			Player.setX(91);
			Player.setY(121);
		   }
	       scene = 3;
		   scene3 = new scene_3();
		   MainGame.MF.getContentPane().add(scene3);
		   scene3.setVisible(true);
		   scene3.start();
		   scene3.requestFocus();
	   }

	/**
	 * @return the miji
	 */
	public static boolean isMiji() {
		return miji;
	}
	/**
	 * @param waigua the waigua to set
	 */
	public static void setMiji(boolean miji) {  //开启关闭秘籍的函数
		MainFrame.miji = miji;
	}
}
