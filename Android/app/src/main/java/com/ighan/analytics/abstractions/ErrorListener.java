package com.ighan.analytics.abstractions;

import com.ighan.analytics.models.ErrorModel;

public interface ErrorListener {

    void errorHappened(ErrorModel error);
}
