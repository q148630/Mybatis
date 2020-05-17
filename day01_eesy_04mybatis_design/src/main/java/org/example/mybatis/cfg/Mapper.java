package org.example.mybatis.cfg;

/**
 * 用於封裝執行的 SQL 語法和結果類型的全限定類名
 */
public class Mapper {

    private String queryString; // SQL
    private String resultType; // 實體類的全限定類名

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }
}
