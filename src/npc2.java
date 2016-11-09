import java.awt.Image;


public class npc2 {
	final int NPCSPEED = 3;
	final int NPCNUM = 15;  //npc的数量
	private int[] x=new int[NPCNUM];   //坐标
	private int[] y=new int[NPCNUM]; 
	private int[] level=new int[NPCNUM];   //鱼等级
	private int[] exp=new int[NPCNUM];     //该等级经验
	private Image[] role=new Image[NPCNUM];     //鱼身图片
	private int[] wid=new int[NPCNUM];    //图片尺寸
	private int[] hei=new int[NPCNUM];
	private int[] lr=new int[NPCNUM];     //鱼水平方向，1为右0为左
	private int[] ud=new int[NPCNUM];           //鱼垂直方向运动
	Image[][] image=MainFrame.Player.image;   //npc和player采用相同的图片集合

	public npc2() {
		// TODO Auto-generated constructor stub
		for(int i=0;i<NPCNUM;i++){
			initial(i);
		}
	}
	//initial方法用来重置第i条鱼的状态
	void initial(int i){
		lr[i] = (Math.random()<0.5)?0:1;
		//下面初始化鱼的位置，while是防止这些鱼初始在player附近的位置
		do{
		   x[i]=(int) (Math.random()*scene_2.getWidth2()*0.85);
		   y[i]=(int) (Math.random()*scene_2.getHeight2()*0.85);
		}while(Math.abs(x[i]-MainFrame.Player.getX()) < 400 && Math.abs(y[i]-MainFrame.Player.getY()) < 200); 
		ud[i]=(int) (Math.random()*3);      
		//垂直运动方向设定，0为不动，1为上，2为下；，
		double tmp=Math.random();
		 //在场景二中的npc经验比例设定
		level[i]=(tmp<0.1)?1:    
			     (tmp<0.5)?2:
			     (tmp<0.8)?3:
			      4;
		if(level[i]==1){
			exp[i]=(int) (100*Math.random());
			 //等级为1的npc尺寸根据经验的设定，注意1级时经验不能从0开始考虑，否则游戏开始时一条鱼也吃不了。
			wid[i]=72+(108-72)*exp[i]/100;         
			hei[i]=27+(41-27)*exp[i]/100;
			role[i]=image[0][lr[i]].getScaledInstance(wid[i], hei[i], Image.SCALE_SMOOTH);
		}
		else
			if(level[i]==2){
				exp[i]=(int) (200*Math.random());
				wid[i]=108+(156-108)*exp[i]/200;
				hei[i]=60+(87-60)*exp[i]/200;
				role[i]=image[1][lr[i]].getScaledInstance(wid[i], hei[i], Image.SCALE_SMOOTH);;
			}
	    else
		    if(level[i]==3){
		    	exp[i]=(int) (300*Math.random());
				wid[i]=156+(216-156)*exp[i]/300;
				hei[i]=117+(162-117)*exp[i]/300;
				role[i]=image[2][lr[i]].getScaledInstance(wid[i], hei[i], Image.SCALE_SMOOTH);;
		    }
		else
			if(level[i]==4){
				exp[i]=(int) (400*Math.random());
				wid[i]=216+(400-216)*exp[i]/400;
				hei[i]=87+(162-87)*exp[i]/400;
				role[i]=image[3][lr[i]].getScaledInstance(wid[i], hei[i], Image.SCALE_SMOOTH);
			}
	}
	public void change(){         
		//每次场景重画时调用此方法来改变每个npc的位置
		for(int i=0;i<NPCNUM;i++){
			if(ud[i]==1)             
				//每条鱼都向原来上下方向游动,若不超出边界，则有1/200的可能改变方向，若超出则直接转向
				if(y[i]>0){
					y[i]-=NPCSPEED;
					if(Math.random()*200<1)   //这里即为取随机数看是否转上下方向
						ud[i]=(Math.random()<0.5)?0:2;
				}
				else
					ud[i]=(Math.random()<0.5)?0:2;
			else
				if(ud[i]==2)
					if(y[i]<scene_2.getHeight2()-hei[i]){
						y[i]+=NPCSPEED;
						if(Math.random()*200<1)
							ud[i]=(Math.random()<0.5)?1:0;
					}
					else
						ud[i]=(Math.random()<0.5)?1:0;
				else
					if(ud[i]==0&&Math.random()*200<1){         
						//若原来状态为水平，则改变方向前必须考虑改变方向后是否会超出边界
						if(y[i]>0&&y[i]<scene_2.getHeight2()-hei[i]){
								ud[i]=(Math.random()<0.5)?1:2;
						}
						else
							if(y[i]>0)
									ud[i]=2;
							else
								if(y[i]<scene_2.getHeight2()-hei[i])
										ud[i]=1;
					}
			if(lr[i]==0)          
				//左右方向的改变同上，只是改变方向的话就必须同时改变鱼的图
				if(x[i]>0){
					x[i]-=NPCSPEED;
					if(Math.random()*200<1){
						lr[i]=1;
						role[i]=image[level[i]-1][lr[i]].getScaledInstance(wid[i], hei[i], Image.SCALE_SMOOTH);
					}
				}
				else{
					lr[i]=1;
					role[i]=image[level[i]-1][lr[i]].getScaledInstance(wid[i], hei[i], Image.SCALE_SMOOTH);
				}
			else
				if(lr[i]==1)
					if(x[i]<scene_2.getWidth2()-wid[i]){
						x[i]+=NPCSPEED;
						if(Math.random()*200<1){
							lr[i]=0;
							role[i]=image[level[i]-1][lr[i]].getScaledInstance(wid[i], hei[i], Image.SCALE_SMOOTH);
						}
					}
					else{
						lr[i]=0;
						role[i]=image[level[i]-1][lr[i]].getScaledInstance(wid[i], hei[i], Image.SCALE_SMOOTH);
					}
			//下面将处理player和npc碰撞时的处理
			int px=MainFrame.Player.getX();
			int py=MainFrame.Player.getY();
			int pw=MainFrame.Player.getWid();
			int ph=MainFrame.Player.getHei();
			int maxlevel = MainFrame.Player.getLevel() > level[i]? MainFrame.Player.getLevel() : level[i];
			if(Math.abs(x[i]+wid[i]/2-px-pw/2)<(wid[i]+pw)/2-maxlevel*maxlevel&&Math.abs(y[i]+hei[i]/2-py-ph/2)<(hei[i]+ph)/2-2*maxlevel*maxlevel){
				//碰撞后考虑哪条鱼更大
				if((level[i]<MainFrame.Player.getLevel())||(level[i]==MainFrame.Player.getLevel()&&exp[i]<=MainFrame.Player.getExp())||MainFrame.isMiji()){
					int addexp=0;
					if(level[i]==1)
						addexp=3+(exp[i]+200)/50;
					else
					if(level[i]==2)
						addexp=6+exp[i]/12;
					else
					if(level[i]==3)
						addexp=10+exp[i]/6;
					else
					if(level[i]==4)
						addexp=17+exp[i]/3;
					addexp=addexp/MainFrame.Player.getLevel();
					//以上是设置吃不同等级鱼的不同经验加成，最后一句因为增加的经验和本体等级有关
					MainFrame.Player.addExp(addexp);
					//下面删除被吃的鱼，并重新加上另一条鱼
					initial(i);
				}
				//下面考虑player被吃掉
				else{
					MainFrame.scene2.stop();
					MainFrame.EP.setVisible(true);
					MainFrame.scene2.setVisible(false);
				}
			}
		}
	}
	/**
	 * @return the role
	 */
	public Image getRole(int i) {
		return role[i];
	}
	/**
	 * @param role the role to set
	 */
	public void setRole(Image[] role) {
		this.role = role;
	}
	/**
	 * @return the x
	 */
	public int getX(int i) {
		return x[i];
	}
	/**
	 * @param x the x to set
	 */
	public void setX(int[] x) {
		this.x = x;
	}
	/**
	 * @return the y
	 */
	public int getY(int i) {
		return y[i];
	}
	/**
	 * @param y the y to set
	 */
	public void setY(int[] y) {
		this.y = y;
	}
	
}
