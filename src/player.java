import java.awt.*;
import javax.swing.*;


public class player {
	final int pspeed = 4;
	private static int x,y;   //坐标
	private static int level;   //鱼等级
	private static int exp;     //该等级经验
	private static Image role,role0,role1;     //鱼身图片，且role0,1为两个方向时的图片
	private static int wid,hei;    //图片尺寸
	private static int dir;     //鱼方向，1为右0为左
	ImageIcon tmp;
	Image[][] image=new Image[4][2]; 

	public player() {
		level = 1;
		exp = 0;
		dir = 1;
		tmp = new ImageIcon(this.getClass().getResource("image/fish1z.gif"));
		image[0][0] = tmp.getImage();
		tmp = new ImageIcon(this.getClass().getResource("image/fish1y.gif"));
		image[0][1] = tmp.getImage();
		tmp = new ImageIcon(this.getClass().getResource("image/fish2z.gif"));
		image[1][0] = tmp.getImage();
		tmp = new ImageIcon(this.getClass().getResource("image/fish2y.gif"));
		image[1][1] = tmp.getImage();
		tmp = new ImageIcon(this.getClass().getResource("image/fish3z.gif"));
		image[2][0] = tmp.getImage();
		tmp = new ImageIcon(this.getClass().getResource("image/fish3y.gif"));
		image[2][1] = tmp.getImage();
		tmp = new ImageIcon(this.getClass().getResource("image/fish4z.gif"));
		image[3][0] = tmp.getImage();
		tmp = new ImageIcon(this.getClass().getResource("image/fish4y.gif"));
		image[3][1] = tmp.getImage();
		wid=72;
		hei=27;
		//鱼大小缩放到初始
		role0=image[0][0].getScaledInstance(wid, hei, Image.SCALE_SMOOTH);       //role0和role1用于储存该状态下两个方向的图片，若方向改变直接在这两个之间切换
		role1=image[0][1].getScaledInstance(wid, hei, Image.SCALE_SMOOTH);
		role=(dir==1)?role1:role0;
		// TODO Auto-generated constructor stub
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		 //每次改变player的位置时，都要考虑player的位置是否超出了场景，超出了的话则此次改变不生效
		if(x<=0)                         
			player.x=0;
		else if(x>scene_1.getWidth1()-wid)
			player.x=scene_1.getWidth1()-wid;
		else
		    player.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		//同x改变的原理
		if(y<=0)                         
			player.y=0;
		else if(y>scene_1.getHeight1()-hei)
			player.y=scene_1.getHeight1()-hei;
		else
		    player.y = y;
	}

	public int getLevel() {
		return level;
	}
	
	public int getExp() {
		return exp;
	}

	public void addExp(int addexp) {
		exp+=addexp;
		if(level==1){
			//每次经验改变时要考虑是否升级,若不升级，则直接改变大小
			if(exp<100){
				wid=72+(108-72)*exp/100;        
				hei=27+(41-27)*exp/100;
				role0=image[0][0].getScaledInstance(wid, hei, Image.SCALE_SMOOTH);   
				role1=image[0][1].getScaledInstance(wid, hei, Image.SCALE_SMOOTH);
				role=(dir==1)?role1:role0;
			}
		//升级则改变图片和大小
			else{
				this.level=2;
				exp=exp-100;
				wid=108+(156-108)*exp/200;
				hei=60+(87- 60)*exp/200;
				role0=image[1][0].getScaledInstance(wid, hei, Image.SCALE_SMOOTH);   
				role1=image[1][1].getScaledInstance(wid, hei, Image.SCALE_SMOOTH);
				role=(dir==1)?role1:role0;
			}
		}
		else 
			if(level==2){
				if(exp<200){
					wid=108+(156-108)*exp/200;
					hei=60+(87- 60)*exp/200;
					role0=image[1][0].getScaledInstance(wid, hei, Image.SCALE_SMOOTH);   
					role1=image[1][1].getScaledInstance(wid, hei, Image.SCALE_SMOOTH);
					role=(dir==1)?role1:role0;
				}
			//升级则改变图片和大小
				else{
					this.level=3;
					exp=exp-200;
					wid=156+(216-156)*exp/200;
					hei=117+(162-117)*exp/200;
					role0=image[2][0].getScaledInstance(wid, hei, Image.SCALE_SMOOTH);   
					role1=image[2][1].getScaledInstance(wid, hei, Image.SCALE_SMOOTH);
					role=(dir==1)?role1:role0;
				}
			}
		else 
			if(level==3){
				if(exp<300){
					wid=156+(216-156)*exp/200;
					hei=117+(162-117)*exp/200;
					role0=image[2][0].getScaledInstance(wid, hei, Image.SCALE_SMOOTH);   
					role1=image[2][1].getScaledInstance(wid, hei, Image.SCALE_SMOOTH);
					role=(dir==1)?role1:role0;
				}
			//升级则改变图片和大小
				else{
					this.level=4;
					exp=exp-300;
					wid=216+(400-216)*exp/300;
					hei=87+(162-87)*exp/300;
					role0=image[3][0].getScaledInstance(wid, hei, Image.SCALE_SMOOTH);   
					role1=image[3][1].getScaledInstance(wid, hei, Image.SCALE_SMOOTH);
					role=(dir==1)?role1:role0;
				}
			}
		else 
			if(level==4){
				if(exp<400){
					wid=216+(400-216)*exp/300;
					hei=87+(162-87)*exp/300;
					role0=image[3][0].getScaledInstance(wid, hei, Image.SCALE_SMOOTH);   
					role1=image[3][1].getScaledInstance(wid, hei, Image.SCALE_SMOOTH);
					role=(dir==1)?role1:role0;
				}
				else{
					//如果经验超过400，则不再增加
					int exp0=exp;
					exp=400;
					wid=216+(400-216)*exp/300;
					hei=87+(162-87)*exp/300;
					role0=image[3][0].getScaledInstance(wid, hei, Image.SCALE_SMOOTH);   
					role1=image[3][1].getScaledInstance(wid, hei, Image.SCALE_SMOOTH);
					role=(dir==1)?role1:role0;
				}
			}
	}

	/**
	 * @return the role
	 */
	public Image getRole() {
		return role;
	}

	/**
	 * @return the wid
	 */
	public int getWid() {
		return wid;
	}

	/**
	 * @param wid the wid to set
	 */
	public void setWid(int wid) {
		player.wid = wid;
	}

	/**
	 * @return the hei
	 */
	public int getHei() {
		return hei;
	}

	/**
	 * @param hei the hei to set
	 */
	public void setHei(int hei) {
		player.hei = hei;
	}

	/**
	 * @return the dir
	 */
	public int getDir() {
		return dir;
	}

	/**
	 * @param dir the dir to set
	 */
	public void setDir(int dir) {
		if(player.dir != dir){           
			//每次方向改变时，改变player的图片并同时缩放到原来大小。
		    player.dir = dir;
		    role=(dir==1)?role1:role0;	
		}
	}


}
