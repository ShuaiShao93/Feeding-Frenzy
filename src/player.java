import java.awt.*;
import javax.swing.*;


public class player {
	final int pspeed = 4;
	private static int x,y;   //����
	private static int level;   //��ȼ�
	private static int exp;     //�õȼ�����
	private static Image role,role0,role1;     //����ͼƬ����role0,1Ϊ��������ʱ��ͼƬ
	private static int wid,hei;    //ͼƬ�ߴ�
	private static int dir;     //�㷽��1Ϊ��0Ϊ��
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
		//���С���ŵ���ʼ
		role0=image[0][0].getScaledInstance(wid, hei, Image.SCALE_SMOOTH);       //role0��role1���ڴ����״̬�����������ͼƬ��������ı�ֱ����������֮���л�
		role1=image[0][1].getScaledInstance(wid, hei, Image.SCALE_SMOOTH);
		role=(dir==1)?role1:role0;
		// TODO Auto-generated constructor stub
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		 //ÿ�θı�player��λ��ʱ����Ҫ����player��λ���Ƿ񳬳��˳����������˵Ļ���˴θı䲻��Ч
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
		//ͬx�ı��ԭ��
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
			//ÿ�ξ���ı�ʱҪ�����Ƿ�����,������������ֱ�Ӹı��С
			if(exp<100){
				wid=72+(108-72)*exp/100;        
				hei=27+(41-27)*exp/100;
				role0=image[0][0].getScaledInstance(wid, hei, Image.SCALE_SMOOTH);   
				role1=image[0][1].getScaledInstance(wid, hei, Image.SCALE_SMOOTH);
				role=(dir==1)?role1:role0;
			}
		//������ı�ͼƬ�ʹ�С
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
			//������ı�ͼƬ�ʹ�С
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
			//������ı�ͼƬ�ʹ�С
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
					//������鳬��400����������
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
			//ÿ�η���ı�ʱ���ı�player��ͼƬ��ͬʱ���ŵ�ԭ����С��
		    player.dir = dir;
		    role=(dir==1)?role1:role0;	
		}
	}


}
