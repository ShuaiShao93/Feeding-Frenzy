import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
public class MainFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static final int Framewidth=800;      //������Ϸ����Ĵ�С
	static final int Frameheight=600;
	static player Player;
	static int scene = 1;   //��¼��ǰ����
	static scene_1 scene1;
	static scene_2 scene2;
	static scene_3 scene3;
	static StartPanel SP;
	static PausePanel PP;
	static EndPanel EP;
	private static boolean miji = false;   //������Ϸ����ؼ�����s�����ɿ�����ң��򿪺���Գ��κ���
	public MainFrame(){
		this.setSize(Framewidth+5,Frameheight+20);   //������Ե
		this.setLayout(null);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLocationRelativeTo(null);
	    this.setResizable(false);
	    this.setVisible(true);
	    SP = new StartPanel();         //�򿪿�ʼ�˵�
	    PP = new PausePanel();
	    EP = new EndPanel();
	    this.getContentPane().add(SP);
	    this.getContentPane().add(PP);
	    this.getContentPane().add(EP);
	    SP.setVisible(true);
	    PP.setVisible(false);
	    EP.setVisible(false);
        SP.start.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){    //��StartPanel��start��ť���Ӽ���������������һ
				Player=new player();
				Player.setX(MainFrame.Framewidth/2);
				Player.setY(979/2);   
				scene1=new scene_1();     //��������1
				getContentPane().add(scene1);
				scene1.start();  //����һ�߳�����
				scene1.setVisible(true);
				SP.setVisible(false);
			}
		});
        SP.end.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){    //��StartPanel��end��ť���Ӽ�������������Ϸ
				System.exit(0); 
			}
		});
        PP.start.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){    //��PausePanel��start��ť���Ӽ���������������һ
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
			public void actionPerformed(ActionEvent e){    //��PausePanel��end��ť���Ӽ�������������Ϸ
				System.exit(0); 
			}
		});
        EP.restart.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){    //��EndPanel��restart��ť���Ӽ���������������һ
				Player=new player();
				Player.setX(MainFrame.Framewidth/2);
				Player.setY(979/2);   
				scene1=new scene_1();     //��������1
				getContentPane().add(scene1);
				scene1.start();  //����һ�߳�����
				scene1.setVisible(true);
				EP.setVisible(false);
			}
		});
        EP.end.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){    //��EndPanel��end��ť���Ӽ�������������Ϸ
				System.exit(0); 
			}
		});
        
	}
	//�ӳ�����player���ﴫ����ʱ���Ե������·������ı䳡��
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
		   scene1.requestFocus();       //�õ�����ʹ��������Ч
	   }
   static void toScene2(int src){
	   if(src == 1){
		   scene1.stop();   //��ֹ�߳�
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
	        scene2.stop();   //��ֹ�߳�
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
	public static void setMiji(boolean miji) {  //�����ر��ؼ��ĺ���
		MainFrame.miji = miji;
	}
}
