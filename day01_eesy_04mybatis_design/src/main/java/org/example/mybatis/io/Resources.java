package org.example.mybatis.io;

import java.io.InputStream;

/**
 * 使用類載入器讀取配置文件的類
 */
public class Resources {

    /**
     * 方法: 根據傳入的參數，取得一個字節輸入流
     * @param filePath
     * @return java.io.InputStream
     */
    public static InputStream getResourceAsStream(String filePath) {
        return Resources.class.getClassLoader().getResourceAsStream(filePath);
    }

}
