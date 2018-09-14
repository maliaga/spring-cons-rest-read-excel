/*
 * Copyright 2012-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package hello;

import static org.assertj.core.api.Assertions.assertThat;

import hello.utils.ReadExcelUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {
	
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ReadExcelUtil readExcelUtil;

	@Test
	public void contextLoads() {
		assertThat(restTemplate).isNotNull();
	}

	@Test
	public void deberiaLeerExcel(){
		List<String[]> arrayDatosExcel = readExcelUtil.readExcelFileToArray(new File("/home/maliaga/Downloads/PaisesIdiomasMonedas.xls"));
		int r = 0;
		for (String[] next : arrayDatosExcel) {
			System.out.print("Array Row: " + r++ + " -> ");
			for (int c = 0; c < next.length; c++) {
				System.out.print("[Column " + c + ": " + next + "] ");
			}
			System.out.println();
		}
	}

}
