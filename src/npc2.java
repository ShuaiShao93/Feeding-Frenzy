import java.awt.Image;


public class npc2 {
	final int NPCSPEED = 3;
	final int NPCNUM = 15;  //npc������
	private int[] x=new int[NPCNUM];   //����
	private int[] y=new int[NPCNUM]; 
	private int[] level=new int[NPCNUM];   //��ȼ�
	private int[] exp=new int[NPCNUM];     //�õȼ�����
	private Image[] role=new Image[NPCNUM];     //����ͼƬ
	private int[] wid=new int[NPCNUM];    //ͼƬ�ߴ�
	private int[] hei=new int[NPCNUM];
	private int[] lr=new int[NPCNUM];     //��ˮƽ����1Ϊ��0Ϊ��
	private int[] ud=new int[NPCNUM];           //�㴹ֱ�����˶�
	Image[][] image=MainFrame.Player.image;   //npc��player������ͬ��ͼƬ����

	public npc2() {
		// TODO Auto-generated constructor stub
		for(int i=0;i<NPCNUM;i++){
			initial(i);
		}
	}
	//initial�����������õ�i�����״̬
	void initial(int i){
		lr[i] = (Math.random()<0.5)?0:1;
		//�����ʼ�����λ�ã�while�Ƿ�ֹ��Щ���ʼ��player������λ��
		do{
		   x[i]=(int) (Math.random()*scene_2.getWidth2()*0.85);
		   y[i]=(int) (Math.random()*scene_2.getHeight2()*0.85);
		}while(Math.abs(x[i]-MainFrame.Player.getX()) < 400 && Math.abs(y[i]-MainFrame.Player.getY()) < 200); 
		ud[i]=(int) (Math.random()*3);      
		//��ֱ�˶������趨��0Ϊ������1Ϊ�ϣ�2Ϊ�£���
		double tmp=Math.random();
		 //�ڳ������е�npc��������趨
		level[i]=(tmp<0.1)?1:    
			     (tmp<0.5)?2:
			     (tmp<0.8)?3:
			      4;
		if(level[i]==1){
			exp[i]=(int) (100*Math.random());
			 //�ȼ�Ϊ1��npc�ߴ���ݾ�����趨��ע��1��ʱ���鲻�ܴ�0��ʼ���ǣ�������Ϸ��ʼʱһ����Ҳ�Բ��ˡ�
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
		//ÿ�γ����ػ�ʱ���ô˷������ı�ÿ��npc��λ��
		for(int i=0;i<NPCNUM;i++){
			if(ud[i]==1)             
				//ÿ���㶼��ԭ�����·����ζ�,���������߽磬����1/200�Ŀ��ܸı䷽����������ֱ��ת��
				if(y[i]>0){
					y[i]-=NPCSPEED;
					if(Math.random()*200<1)   //���ＴΪȡ��������Ƿ�ת���·���
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
						//��ԭ��״̬Ϊˮƽ����ı䷽��ǰ���뿼�Ǹı䷽����Ƿ�ᳬ���߽�
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
				//���ҷ���ĸı�ͬ�ϣ�ֻ�Ǹı䷽��Ļ��ͱ���ͬʱ�ı����ͼ
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
			//���潫����player��npc��ײʱ�Ĵ���
			int px=MainFrame.Player.getX();
			int py=MainFrame.Player.getY();
			int pw=MainFrame.Player.getWid();
			int ph=MainFrame.Player.getHei();
			int maxlevel = MainFrame.Player.getLevel() > level[i]? MainFrame.Player.getLevel() : level[i];
			if(Math.abs(x[i]+wid[i]/2-px-pw/2)<(wid[i]+pw)/2-maxlevel*maxlevel&&Math.abs(y[i]+hei[i]/2-py-ph/2)<(hei[i]+ph)/2-2*maxlevel*maxlevel){
				//��ײ�������������
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
					//���������óԲ�ͬ�ȼ���Ĳ�ͬ����ӳɣ����һ����Ϊ���ӵľ���ͱ���ȼ��й�
					MainFrame.Player.addExp(addexp);
					//����ɾ�����Ե��㣬�����¼�����һ����
					initial(i);
				}
				//���濼��player���Ե�
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
