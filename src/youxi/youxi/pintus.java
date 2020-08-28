package youxi;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
 
import javax.imageio.ImageIO;
import javax.swing.*;
class Palace{                           //算出最短步数
	String start;
	String end;
	int result=-1;
	public int Palacea(String shuju){
		this.start = shuju;
		end = "12345678.";
		HashMap<String, Integer> memery = new HashMap<>(100000);
		Queue<String> process = new LinkedList<String>();//bfs用到的队列
		memery.put(start,0);
		process.offer(start);
		while(result==-1){
			String cur = process.poll();
			char[] a=cur.toCharArray();
			int count=0;
			for(int b=0;b<a.length;b++) {
				count++;
				System.out.print(a[b]+"  ");
				if(count%3==0)System.out.println();
				}
			System.out.println("++++++++++++++");			
			int tmp = 0;
			while(cur.charAt(tmp)!='.'){
				tmp++;
			}						
			int[] d = {-3,3,-1,1};//方向数组，分别表示上下左右
			for(int i=0;i<4;i++){
				int p = tmp+d[i];
				int chengji = tmp*p;
				if(p>-1&&p<9&&chengji!=6&&chengji!=30){        //2-->3,3-->2,5-->6,6-->5跨行了，要去掉
					String change = cur;
					char c = cur.charAt(p);
					change = cur.replace('.', '*');//交换String中的两个字符，借助中间字符‘*’
					change = change.replace(c,'.');
					change = change.replace('*',c);
					if(change.equals(end)){				
						result = memery.get(cur)+1;
					}
					if(!memery.containsKey(change)){
						memery.put(change,memery.get(cur)+1);
						process.add(change);
					}
				}
			}
		}
		System.out.println(result);
       return result;
	}
}
class tu extends JButton {
int ID;
int nowID;
 int IMAGEWIDTH;
 int IMAGEHEIGHT;
int nowx;
int nowy;
Icon icon;
public tu(Icon icon) {
	this.setIcon(icon);
}
public tu(Icon icon, int id, int imagewidth, int height,int nowx,int nowy,int nowid)//构造函数初始化，传入两个参数，一个是图像的图标，一个是该按钮的数组ID
{
	this.setIcon(icon);
    this.ID = id;
    this.nowID = nowid;
    this.IMAGEWIDTH = imagewidth;
    this.IMAGEHEIGHT = height;
    this.setSize(IMAGEWIDTH, IMAGEHEIGHT);
    this.nowx=nowx;
    this.nowy=nowy;
    this.icon=icon;    
}
public Icon geticon() {
    return icon;
}
public int getID() {
    return ID;
}
public int getnowID() {
    return nowID;
}
public int getx()
{
    return this.getBounds().x;
}
 
public int gety()
{
    return this.getBounds().y;
}
public int getnowx()
{
    return nowx;
}
public int getnowy()
{
    return nowy;
}
}
 
 class pintus extends JFrame  implements  MouseListener,  ActionListener {
	private JButton strat,strat1,strat2,yuan,restart,save,readsave,tishi;      
	private JLabel raw;      
	private tu tu[]=new tu[9];              
	private tu tuz[]=new tu[9];
	JLabel tishikuan;                         
	private int ID;
	ImageIcon icon1=null;
	int jishu=0;
	JPanel j1=new JPanel();
	JPanel j2=new JPanel();
	JPanel j3=new JPanel();
	public pintus() {
		BufferedImage tutu=null;
		ImageIcon icon = null;
		
		BufferedImage  bufnew=null;
		int w=0;
		int h=0;
		int m = 0;		
		//////////////////////////////////// //jframe设置基本属性
		setTitle("欢迎来到拼图小游戏1.0版本！");           
 
		////////////////////////////////////添加布局
		j1.setLayout(new GridLayout(3, 3));  //设置中间流式布局
		//j2.setLayout(new GridLayout(1, 4));  //设置中间流式布局	  
		///////////////////////////////////图片切割并存入
		 try {
			 tutu = ImageIO.read(new File("C:\\Users\\Rainfarn\\Desktop\\1430974322-3482.jpg"));
			 w=tutu.getWidth()/3;
			 h=tutu.getHeight()/3;
			 	setSize(tutu.getWidth(),tutu.getHeight()+175);       
			 	//System.out.println(w+" "+h);
		} catch (IOException e) {
	        System.out.println("错误");
			e.printStackTrace();
		}
	    
		 for(int i = 0; i < 3 ;i++)
	        {
	            for(int j = 0; j < 3; j++)
	            {	
	             m=j*3+i;	           
		         bufnew = tutu.getSubimage(w*i, h*j, w, h);
                 icon = new ImageIcon(bufnew);
                 tu [m]=new tu(icon,m,w,h,i,j,m);
                 tu [m].setLocation(w*j, h*i);
                 tuz [m]=new tu(icon,m,w,h,i,j,m);
                 tuz [m].setLocation(w*j, h*i);                
                 if(m==8)
                 {  
                	 icon=new ImageIcon("D:\\图片\\Saved Pictures\\1.jpg");                	 
                	 tu[m]=new tu(icon,m,w,h,i,j,m);
                	 tuz[m]=new tu(icon,m,w,h,i,j,m);
                	 }
	            }
	        }
		
		j2.add(strat=new JButton("简单"));
		j2.add(strat1=new JButton("中等"));
		j2.add(strat2=new JButton("复杂"));
		j2.add(yuan=new JButton("还原"));
		j2.add(tishi=new JButton("提示"));
		//j2.add(restart=new JButton("重置"));
		//j2.add(save=new JButton("保存进度"));
		//j2.add(readsave=new JButton("读取进度"));		
		icon=new ImageIcon("D:\\图片\\Saved Pictures\\2.jpg"); 		
		j2.add(raw=new JLabel(icon));	
		strat.addActionListener(this); 
		strat1.addActionListener(this);
		strat2.addActionListener(this);
		yuan.addActionListener(this);
		tishi.addActionListener(this);		
		j3.add(tishikuan=new JLabel("系统提示：请选择游戏模式"));		
		 icon1=(ImageIcon) tu[8].geticon();
         zairu();
		//////////////////////////////////////////
         
   setLocationRelativeTo(null);		
   setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   setVisible(true);
   setResizable(false); 	
	}
	/////////////////////////////////设置按钮
	public void zairu() {
	for(int a=0;a<9;a++)                                //输入图片	
	{                     			
		j1.add(tu[a]);
		tu [a].addMouseListener(this);                //设置鼠标事件
	}
	add(j1,BorderLayout.CENTER);
	add(j2,BorderLayout.PAGE_START);
	add(j3,BorderLayout.PAGE_END);	
	}
	
	public void qingkong() {
     removeAll();	
	}
	
	public void moves(tu t) {
	
		int a=t.getnowID();
		icon1=new ImageIcon("D:\\图片\\Saved Pictures\\1.jpg");  
		if(a==0) {	 
	if(tu[a+1].getID()==8) {  tu[a+1].setIcon(t.geticon()); tu[a+1].ID=t.getID();   t.setIcon(icon1);t.ID=8;     tu[a+1].icon=t.icon;t.icon=icon1;jishu++;}
	if(tu[a+3].getID()==8) {  tu[a+3].setIcon(t.geticon()); tu[a+3].ID=t.getID();   t.setIcon(icon1);t.ID=8;     tu[a+3].icon=t.icon;t.icon=icon1;jishu++;}   }
		if(a==1) {	 
	if(tu[a+1].getID()==8) {  tu[a+1].setIcon(t.geticon()); tu[a+1].ID=t.getID();   t.setIcon(icon1);t.ID=8;     tu[a+1].icon=t.icon;t.icon=icon1;jishu++;}
	if(tu[a+3].getID()==8) {  tu[a+3].setIcon(t.geticon()); tu[a+3].ID=t.getID();   t.setIcon(icon1);t.ID=8;     tu[a+3].icon=t.icon;t.icon=icon1;jishu++;}  
	if(tu[a-1].getID()==8) {  tu[a-1].setIcon(t.geticon()); tu[a-1].ID=t.getID();   t.setIcon(icon1);t.ID=8;     tu[a-1].icon=t.icon;t.icon=icon1;jishu++;} }
		if(a==2) {	 
	if(tu[a+3].getID()==8) {  tu[a+3].setIcon(t.geticon()); tu[a+3].ID=t.getID();   t.setIcon(icon1);t.ID=8;     tu[a+3].icon=t.icon;t.icon=icon1;jishu++;}  
	if(tu[a-1].getID()==8) {  tu[a-1].setIcon(t.geticon()); tu[a-1].ID=t.getID();   t.setIcon(icon1);t.ID=8;     tu[a-1].icon=t.icon;t.icon=icon1;jishu++;} }
		if(a==3) {	 
	if(tu[a+3].getID()==8) {  tu[a+3].setIcon(t.geticon()); tu[a+3].ID=t.getID();   t.setIcon(icon1);t.ID=8;     tu[a+3].icon=t.icon;t.icon=icon1;jishu++;}  
	if(tu[a-3].getID()==8) {  tu[a-3].setIcon(t.geticon()); tu[a-3].ID=t.getID();   t.setIcon(icon1);t.ID=8;      tu[a-3].icon=t.icon;t.icon=icon1; jishu++;}
	if(tu[a+1].getID()==8) {  tu[a+1].setIcon(t.geticon()); tu[a+1].ID=t.getID();   t.setIcon(icon1);t.ID=8;     tu[a+1].icon=t.icon;t.icon=icon1;jishu++;}     }
		if(a==4) {	
	if(tu[a+3].getID()==8) {  tu[a+3].setIcon(t.geticon()); tu[a+3].ID=t.getID();   t.setIcon(icon1);t.ID=8;     tu[a+3].icon=t.icon;t.icon=icon1;jishu++;}  
	if(tu[a-3].getID()==8) {  tu[a-3].setIcon(t.geticon()); tu[a-3].ID=t.getID();   t.setIcon(icon1);t.ID=8;      tu[a-3].icon=t.icon;t.icon=icon1; jishu++;}
	if(tu[a+1].getID()==8) {  tu[a+1].setIcon(t.geticon()); tu[a+1].ID=t.getID();   t.setIcon(icon1);t.ID=8;     tu[a+1].icon=t.icon;t.icon=icon1;jishu++;} 
	if(tu[a-1].getID()==8) {  tu[a-1].setIcon(t.geticon()); tu[a-1].ID=t.getID();   t.setIcon(icon1);t.ID=8;   tu[a-1].icon=t.icon;t.icon=icon1;jishu++;} 	}
		if(a==5) {	 
	if(tu[a+3].getID()==8) {  tu[a+3].setIcon(t.geticon()); tu[a+3].ID=t.getID();   t.setIcon(icon1);t.ID=8;     tu[a+3].icon=t.icon;t.icon=icon1;jishu++;}  
	if(tu[a-3].getID()==8) {  tu[a-3].setIcon(t.geticon()); tu[a-3].ID=t.getID();   t.setIcon(icon1);t.ID=8;       tu[a-3].icon=t.icon;t.icon=icon1;jishu++;}   
	if(tu[a-1].getID()==8) {  tu[a-1].setIcon(t.geticon()); tu[a-1].ID=t.getID();   t.setIcon(icon1);t.ID=8;   tu[a-1].icon=t.icon;t.icon=icon1;jishu++;} 	     }
		if(a==6) {	 
	if(tu[a-3].getID()==8) {  tu[a-3].setIcon(t.geticon()); tu[a-3].ID=t.getID();   t.setIcon(icon1);t.ID=8;       tu[a-3].icon=t.icon;t.icon=icon1;jishu++;}
	if(tu[a+1].getID()==8) {  tu[a+1].setIcon(t.geticon()); tu[a+1].ID=t.getID();   t.setIcon(icon1);t.ID=8;     tu[a+1].icon=t.icon;t.icon=icon1;jishu++;}   	}
		if(a==7) {	 
	if(tu[a-3].getID()==8) {  tu[a-3].setIcon(t.geticon()); tu[a-3].ID=t.getID();   t.setIcon(icon1);t.ID=8;       tu[a-3].icon=t.icon;t.icon=icon1;jishu++;}
	if(tu[a+1].getID()==8) {  tu[a+1].setIcon(t.geticon()); tu[a+1].ID=t.getID();   t.setIcon(icon1);t.ID=8;    tu[a+1].icon=t.icon;t.icon=icon1; jishu++;} 
	if(tu[a-1].getID()==8) {  tu[a-1].setIcon(t.geticon()); tu[a-1].ID=t.getID();   t.setIcon(icon1);t.ID=8;   tu[a-1].icon=t.icon;t.icon=icon1;jishu++;} 	}
		if(a==8) {	 
	if(tu[a-3].getID()==8) {  tu[a-3].setIcon(t.geticon()); tu[a-3].ID=t.getID();   t.setIcon(icon1);t.ID=8;      tu[a-3].icon=t.icon;t.icon=icon1;jishu++; }
	if(tu[a-1].getID()==8) {  tu[a-1].setIcon(t.geticon()); tu[a-1].ID=t.getID();   t.setIcon(icon1);t.ID=8;   tu[a-1].icon=t.icon;t.icon=icon1;jishu++;} 	}	
		}
	/////////////////////////////////////////////图片移动事件
	  public void mouseClicked(MouseEvent e){            //设置执行事件
		tu t =(tu) e.getSource();
		System.out.println("原本的位置："+t.getID()+"  点击的位置:"+t.getnowID());
		int a=t.getnowID();
        moves(t);
		String s="系统提示：游戏继续"+",点击的块数目标位置为"+t.getID();
	   tishikuan.setText(s);
       
        if(win()&&jishu!=0) {jishu=0;JOptionPane.showConfirmDialog(null, "你赢了！ " ,"拼图",JOptionPane.CANCEL_OPTION); }      
	    
	}
	  private boolean win() {
		  int sum=0;
		for(int s=0;s<9;s++)
		{
			if(tu[s].ID==tu[s].nowID)
			 sum++;
			
		}
		if(sum==9) {
		tishikuan.setText("系统提示：你赢了");
		return true;}
		else
		{
 
		return false;
		}
	}
	public void actionPerformed(ActionEvent e) {        //设置执行事件
			if(e.getSource()==strat) {
				luan();	
				String s="系统提示：游戏开始，简单模式";
				   tishikuan.setText(s);
				for(int a=0;a<9;a++) {
					if(tu[a].ID==8) {System.out.println(0);continue;}
				    System.out.println(tu[a].ID+1);}
				
			}
			if(e.getSource()==strat1) {
				luan1();	
				String s="系统提示：游戏开始，中等模式";
				   tishikuan.setText(s);
				for(int a=0;a<9;a++) {
					if(tu[a].ID==8) {System.out.println(0);continue;}
				System.out.println(tu[a].ID+1);}
			}
			if(e.getSource()==strat2) {
				luan2();
				String s="系统提示：游戏开始，复杂模式";
				   tishikuan.setText(s);
				for(int a=0;a<9;a++) {
					if(tu[a].ID==8) {System.out.print(".");continue;}
				System.out.print(tu[a].ID+1);}
			}
			if(e.getSource()==yuan) {
				String s="系统提示：已经还原，请重新选择游戏模式";
				   tishikuan.setText(s);
                 try {
					huanyuan();
				} catch (IOException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
                
			}
			if(e.getSource()==tishi) {
				if(jishu==0) {
					String s="系统提示：系统利用广度搜索算出解法，需要0步";
					   tishikuan.setText(s);
				}
				else
				{
				String d = "" ;
				for(int a=0;a<9;a++) {
				if(tu[a].ID==8)	
				{d = d+".";continue;}
				d = d+Integer.toString(tu[a].ID+1);
				}
 
				System.out.println(d);
				Palace palace = new Palace();
				
				String s="系统提示：系统利用广度搜索算出解法，需要"+palace.Palacea(d)+"步";
				 tishikuan.setText(s);
 
				}
			}	  
	  } 
	
	private void huanyuan() throws IOException {
		jishu=0;
		for(int a=0;a<9;a++) {						  //显示图		
			tu[tu[a].ID].setIcon(tu[a].geticon());
			
		}	    
	  for(int i = 0; i < 9 ;i++) {             //还原图
	    	tu[i].icon=tuz[i].icon;
		 }
	   for(int i = 0; i < 9 ;i++) {              //还原标记点
			  tu[i].ID=tu[i].nowID=i;
 
		 }		
	}
		                                                      //打乱顺序
	  private void luan() {
		for(int a=0;a<20;a++)
		{
			int b=(int) (Math.random()*9);
			moves(tu[b]);
		} 		
	   }
	  private void luan1() {
		for(int a=0;a<100;a++)
		{
			int b=(int) (Math.random()*9);
			moves(tu[b]);
		} 		
	   }
	  private void luan2() {
		for(int a=0;a<10000;a++)
		{
			int b=(int) (Math.random()*9);
			moves(tu[b]);
		} 		
	   }
	  ///////////////////////////////////
	  public void setIcon(String file, JButton iconButton) {  
	        ImageIcon icon = new ImageIcon(file);  
	        Image temp = icon.getImage().getScaledInstance(iconButton.getWidth(),  
	                iconButton.getHeight(), icon.getImage().SCALE_DEFAULT);  
	        icon = new ImageIcon(temp);  
	        iconButton.setIcon(icon);  
	    }
	////////////////////////////////////////////////
	  @Override
		public void mouseEntered(MouseEvent e) {
			// TODO 自动生成的方法存根
			
		}
		@Override
		public void mouseExited(MouseEvent e) {
			// TODO 自动生成的方法存根
			
		}
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO 自动生成的方法存根
			
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO 自动生成的方法存根
			
		}
/////////////////////////////////////////////////////////////////////
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());		
		}	catch(Exception e) {}
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					new pintus();
				}
				});
		}
	
}
