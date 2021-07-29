package com.jianhao.nacos_server;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@SpringBootTest
class NacosServerApplicationTests {

	@Test
	void contextLoads() {
	}


	@Test
	void test(){
		String str = "2017-08-21";
		String pattern = "^\\\\d{4}-\\\\d{2}-\\\\d{2}/*";

		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(str);
		
		System.out.println("字符串" + str + "    检验结果：" + (m.matches() ? "成功" : "失败"));
	}

}
