package com.nfyg.hswx.web;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nfyg.hswx.R;
import com.nfyg.hswx.utils.httpUtils.BitmapCache;
import com.nfyg.hswx.web.request.AdClickRedirectVolleyRequest;
import com.nfyg.hswx.web.request.JsonWebPostRequest;
import com.nfyg.hswx.web.request.JsonWebPostRequest2;
import com.nfyg.hswx.web.response.JsonArrayResponseParser;
import com.nfyg.hswx.web.response.JsonResponseParser;
import com.nfyg.hswx.web.response.JsonResponseParser2;
import com.nfyg.hswx.web.rplistener.OnArrayResponseListener;
import com.nfyg.hswx.web.rplistener.OnFileDownloadListener;
import com.nfyg.hswx.web.rplistener.OnJSONObjectResponseListener;
import com.nfyg.hswx.web.rplistener.OnResponseListener;
import com.nfyg.hswx.web.rplistener.OnResponseListener2;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * base request
 */
public class BaseWebService {
    private RequestQueue requestQueue;
    private Context context;
    private boolean shouldRetry = true;

    private BitmapCache mBitmapCache;

    private static DefaultRetryPolicy defaultRetryPolicy = new DefaultRetryPolicy(5000, 2, 1f);
    private static DefaultRetryPolicy noRetryPolicy = new DefaultRetryPolicy(5000, 0, 1f);

    public BaseWebService(Context context) {
        requestQueue = Volley.newRequestQueue(context);
        mBitmapCache = new BitmapCache();
        this.context = context;
    }

    public void setShouldRetry(boolean retry) {
        shouldRetry = retry;
    }

    private void resetRetrySetting() {
        shouldRetry = true;
    }

    public void jsonPostRequest(String url, JSONObject jsonObject, Response.Listener<JSONObject>
            listener, Response.ErrorListener errorListener) {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                listener, errorListener);
        request.setShouldCache(false);
        if (shouldRetry) {
            request.setRetryPolicy(defaultRetryPolicy);
        } else {
            request.setRetryPolicy(noRetryPolicy);
        }
        requestQueue.add(request);
        resetRetrySetting();
    }

    public void jsonGetRequest(String url, Response.Listener<JSONObject> listener,
                                  Response.ErrorListener errorListener) {
        JsonObjectRequest request = new JsonObjectRequest(url, null, listener, errorListener);
        request.setShouldCache(false);
        if (shouldRetry) {
            request.setRetryPolicy(defaultRetryPolicy);
        } else {
            request.setRetryPolicy(noRetryPolicy);
        }
        requestQueue.add(request);
        resetRetrySetting();
    }

    private void jsonWebRequest(String url, JSONObject params, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        JsonWebPostRequest jsonWebPostRequest = new JsonWebPostRequest(url, params, listener, errorListener);
        jsonWebPostRequest.setShouldCache(false);
        if (shouldRetry) {
            jsonWebPostRequest.setRetryPolicy(defaultRetryPolicy);
        } else {
            jsonWebPostRequest.setRetryPolicy(noRetryPolicy);
        }
        requestQueue.add(jsonWebPostRequest);
        resetRetrySetting();
    }

    private void jsonWebRequest2(String url, HashMap<String, String> params, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        JsonWebPostRequest2 jsonWebPostRequest = new JsonWebPostRequest2(url, params, listener, errorListener);
        jsonWebPostRequest.setShouldCache(false);
        if (shouldRetry) {
            jsonWebPostRequest.setRetryPolicy(defaultRetryPolicy);
        } else {
            jsonWebPostRequest.setRetryPolicy(noRetryPolicy);
        }
        requestQueue.add(jsonWebPostRequest);
        resetRetrySetting();
    }

    private void stringRequest(String url, Response.Listener<String> listener,
                                     Response.ErrorListener errorListener) {
        StringRequest request = new StringRequest(url, listener, errorListener);
        request.setShouldCache(false);
        if (shouldRetry) {
            request.setRetryPolicy(defaultRetryPolicy);
        } else {
            request.setRetryPolicy(noRetryPolicy);
        }
        requestQueue.add(request);
        resetRetrySetting();
    }

    private void jsonArrayGetRequest(String url, Response.Listener<JSONArray> listener,
                                     Response.ErrorListener errorListener) {
        JsonArrayRequest request = new JsonArrayRequest(url, listener, errorListener);
        request.setShouldCache(false);
        if (shouldRetry) {
            request.setRetryPolicy(defaultRetryPolicy);
        } else {
            request.setRetryPolicy(noRetryPolicy);
        }
        requestQueue.add(request);
        resetRetrySetting();
    }

    private void jsonArrayGetRequestWithPolicy(String url, Response.Listener<JSONArray> listener,
                                               Response.ErrorListener errorListener,
                                               boolean isRetry,
                                               DefaultRetryPolicy customRetryPolicy,
                                               DefaultRetryPolicy customNoRetryPolicy) {
        JsonArrayRequest request = new JsonArrayRequest(url, listener, errorListener);
        request.setShouldCache(false);
        if (isRetry) {
            if(customRetryPolicy != null)
                request.setRetryPolicy(customRetryPolicy);
            else
                request.setRetryPolicy(defaultRetryPolicy);
        } else {
            if(customNoRetryPolicy != null)
                request.setRetryPolicy(customNoRetryPolicy);
            else
                request.setRetryPolicy(noRetryPolicy);
        }
        requestQueue.add(request);
        resetRetrySetting();
    }

    private void imageGetRequest(String url, Response.Listener<Bitmap> listener,
                              Response.ErrorListener errorListener) {
        ImageRequest request = new ImageRequest(url, listener,
                0, 0, Bitmap.Config.RGB_565, errorListener);
        request.setShouldCache(false);
        if (shouldRetry) {
            request.setRetryPolicy(defaultRetryPolicy);
        } else {
            request.setRetryPolicy(noRetryPolicy);
        }
        requestQueue.add(request);
        resetRetrySetting();
    }

    public <T> void jsonPost(String url, JSONObject postData, final JsonResponseParser<T>
            jsonResponseParser, final OnResponseListener<T> listener) {
        jsonPostRequest(url, postData, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    listener.onResponse(jsonResponseParser.parse(jsonObject));
                } catch (JSONException e) {
                    e.printStackTrace();
                    listener.onError(context.getResources().getString(R.string.system_error));
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                listener.onError(volleyError.getMessage());
            }
        });
    }

    public <T> void jsonGet(String url, final JsonResponseParser2<T> jsonResponseParser,
                            final OnResponseListener2<T> listener) {
        jsonGetRequest(url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    listener.onResponse(jsonResponseParser.parse(jsonObject));
                } catch (JSONException e) {
                    e.printStackTrace();
                    listener.onError(context.getResources().getString(R.string.system_error));
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                listener.onError(volleyError.getMessage());
            }
        });
    }

    public void stringGet(String url, final OnResponseListener2<String> listener) {
        stringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                listener.onResponse(s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                listener.onError(volleyError.getMessage());
            }
        });
    }

    public <T> void jsonWebPost(String api, JSONObject params, final JsonResponseParser<T> jsonResponseParser, final OnResponseListener<T> listener) {

        jsonWebRequest(api, params,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        try {
                            listener.onResponse(jsonResponseParser.parse(jsonObject));
                        } catch (JSONException e) {
                            e.printStackTrace();
                            listener.onError("JSONException");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        listener.onError(volleyError.getMessage());
                    }
                }
        );
    }


    public <T> void jsonWebPost2(String api, HashMap<String, String> params, final JsonResponseParser2<T> jsonResponseParser, final OnResponseListener2<T> listener) {
        jsonWebRequest2(api, params,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            listener.onResponse(jsonResponseParser.parse(response));
                        } catch (JSONException e) {
                            e.printStackTrace();
                            listener.onError("JSONException");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        listener.onError(volleyError.getMessage());
                    }
                }
        );
    }
    public <T> void jsonWebPost3(String api, HashMap<String, String> params, final JsonResponseParser2<T> jsonResponseParser, final OnResponseListener2<T> listener) {
        jsonWebRequest2(api, params,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            listener.onResponse(jsonResponseParser.parse(response));
                        } catch (JSONException e) {
                            e.printStackTrace();
                            listener.onError("JSONException");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        listener.onError(volleyError.getMessage());
                    }
                }
        );
    }

    public <T> void jsonArrayGet(String url, final JsonArrayResponseParser<T> parser,
                                 final OnArrayResponseListener<T> listener) {
        jsonArrayGetRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    listener.onResponse(parser.parse(response));
                } catch (JSONException e) {
                    e.printStackTrace();
                    listener.onError(context.getResources().getString(R.string.system_error));
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                listener.onError(volleyError.getMessage());
            }
        });
    }

    public <T> void jsonArrayGetWithPoilcy(String url, final JsonArrayResponseParser<T> parser,
                                 final OnArrayResponseListener<T> listener,
                                 boolean isRetry,
                                 DefaultRetryPolicy retryPolicy,
                                 DefaultRetryPolicy noRetryPolicy) {
        jsonArrayGetRequestWithPolicy(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    listener.onResponse(parser.parse(response));
                } catch (JSONException e) {
                    e.printStackTrace();
                    listener.onError(context.getResources().getString(R.string.system_error));
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                listener.onError(volleyError.getMessage());
            }
        }, isRetry, retryPolicy, noRetryPolicy);
    }

    public <T> void jsonObjectGet(String url, final OnJSONObjectResponseListener listener) {
        jsonGetRequest(url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                listener.onResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                listener.onError(volleyError.getMessage());
            }
        });
    }

    public void imageGet(String url, final OnResponseListener2<Bitmap> listener) {
        imageGetRequest(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap b) {
                listener.onResponse(b);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                listener.onError(volleyError.getMessage());
            }
        });
    }

    private void adClickRedirectRequest(String url, Response.Listener<JSONObject> listener,
                                        Response.ErrorListener errorListener) {
        AdClickRedirectVolleyRequest request = new AdClickRedirectVolleyRequest(url, listener,
                errorListener);
        request.setShouldCache(false);
        request.setRetryPolicy(new DefaultRetryPolicy());
        requestQueue.add(request);
        resetRetrySetting();
    }

    public <T> void adClickRedirectVolleyGet(String url,
                                             final JsonResponseParser2<T> parser, final OnResponseListener2<T> listener) {
        adClickRedirectRequest(url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    listener.onResponse(parser.parse(response));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onError(error.getMessage());
            }
        });
    }

    public void imageDownload(String url, final OnFileDownloadListener<Bitmap> listener) {
        ImageRequest ir = new ImageRequest(url ,new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                listener.onResponse(response);
            }
        }, 0, 0, ImageView.ScaleType.CENTER_CROP, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onError(error.getMessage());
            }
        });
        ir.setShouldCache(false);
        if (shouldRetry) {
            ir.setRetryPolicy(defaultRetryPolicy);
        } else {
            ir.setRetryPolicy(noRetryPolicy);
        }
        requestQueue.add(ir);
        resetRetrySetting();
    }

    public void displayImg(ImageView imageView,String imageUrl){

        ImageLoader imageLoader = new ImageLoader(requestQueue, mBitmapCache);
        ImageLoader.ImageListener listener = ImageLoader.getImageListener(imageView,R.drawable.ic_full_image_failed, R.drawable.ic_launcher);
        imageLoader.get(imageUrl, listener);
        //指定图片允许的最大宽度和高度
        //imageLoader.get(imageUrl,listener, 200, 200);
    }

}
