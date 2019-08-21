using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using System;
using AOT;
using System.Runtime.InteropServices;

public class SystemIOS
{
#if UNITY_IOS && !UNITY_EDITOR
    //C语言的接口声明
    [System.Runtime.InteropServices.DllImport("__Internal")]
    static extern void outputAppendString(string str1, string str2);

    [System.Runtime.InteropServices.DllImport("__Internal")]
    static extern void outputAppendString2(string str1, string str2, IntPtr resultHandler);

    [System.Runtime.InteropServices.DllImport("__Internal")]
    static extern bool isLiuHai();

    void callback(string resultStr)
    {
        Debug.Log("result string = " + resultStr);
#if UNITY_IOS
      ResultHandler handler = new ResultHandler(resultHandler);
      IntPtr fp = Marshal.GetFunctionPointerForDelegate(handler);
        outputAppendString2("hello", "world!!!", fp);
#endif
    }

    [UnmanagedFunctionPointer(CallingConvention.Cdecl)]
    public delegate void ResultHandler(string resultString);

    [MonoPInvokeCallback(typeof(ResultHandler))]
    static void resultHandler(string resultStr)
    {
        Debug.Log("oc delegate回调" + resultStr);
    }
#endif

    //----------------------public----
    public static bool isLiuHaiScreen()
    {
        bool result = false;
#if UNITY_IOS && !UNITY_EDITOR
        //outputAppendString("hello", "world");
        result = isLiuHai();
#endif
        return result;
    }


}
