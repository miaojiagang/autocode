import com.leigang.bi.fast.autocode.AutoCode;

/**
 * Created by Administrator on 2017/11/8.
 */
public class Test {
    public static void main(String[] s) {
        try {
            AutoCode.compiler(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}