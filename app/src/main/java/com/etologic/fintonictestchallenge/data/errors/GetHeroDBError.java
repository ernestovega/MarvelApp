package com.etologic.fintonictestchallenge.data.errors;

import com.etologic.fintonictestchallenge.data.ErrorBase;

/**
 * Created by ernesto.vega on 18/06/2017.
 */

public class GetHeroDBError implements ErrorBase {

    private String sError;

    public GetHeroDBError(String message) {
        sError = message;
    }

    @Override
    public String ErrorDescription() {
        return sError;
    }
}
