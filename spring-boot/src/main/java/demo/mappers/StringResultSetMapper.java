package demo.mappers;

import spwrap.mappers.ResultSetMapper;
import spwrap.result.Result;

public class StringResultSetMapper implements ResultSetMapper<String> {
    @Override
    public String map(Result<?> result) {
        return result.getString(1);
    }
}
