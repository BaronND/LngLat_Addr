package property;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class PropertiesUtils {
	
	/**
	 * 类级 的 内部类， 也就是 静态的 成员式 内部类，
	 * 该 内部类的 实例  与 外部类的 实例 没有 绑定 关系，
	 * 而且 只有 被调用的 时 才会  装载， 从而 实现了 延迟 加载；
	 * @author yazi
	 *
	 */
	private static class SingletonHolder{
		/**
		 * 静态初始化器， 由 JVM 来 保证 线程 安全
		 */
		private static PropertiesConfiguration instance = new PropertiesConfiguration();
	}
	/**
	 * 私有化构造 方法
	 */
	private PropertiesUtils(){ }
	
	public static PropertiesConfiguration getInstance(){
		SingletonHolder.instance.setEncoding("UTF-8");
		try {
			SingletonHolder.instance.load("table_name_config.properties");
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
		return SingletonHolder.instance;
	}

	/*public static PropertiesConfiguration propsConfig = new PropertiesConfiguration();

	static {
		propsConfig.setEncoding("UTF-8");
	}
    
    try {
		propsConfig.load("log4j.properties");
	} catch (ConfigurationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    String strValue=propsConfig.getString("log4j.appender.D.Threshold");
    System.out.println(strValue);*/
}
