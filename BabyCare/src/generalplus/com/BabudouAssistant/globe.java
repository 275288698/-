package generalplus.com.BabudouAssistant;


public class globe {

	public static float voice=1.0f;
	public static String username,pwd ;//用户名和密码
	public static int label;//跳过选择产品型号的标志
	public static int label2;//跳过注册登陆界面的标志
	public static int x;	//1代表倒屏，2代表竖屏
	public static int n=1;  //1代表直接跳过登录、注册，2代表不跳过
	public static String sign="acquire";//向服务器请求收藏夹里面的内容时发送的标志
	public static String json;//获取收藏夹里面的内容保存在json的字符串当中
	public static String []caimiyuname=new String[100];
	public static String []caimiyukey=new String[100];
	public static int [] cmd1=new int[100];
	public static int [] cmd2=new int[100];
	public static int itemIndex;
}