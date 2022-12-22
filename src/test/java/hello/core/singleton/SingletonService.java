package hello.core.singleton;


public class SingletonService {
    //static 으로 자기자신 생성
    private static final SingletonService instance = new SingletonService();
    //객체 접근 방법
    public static SingletonService getInstance() {
        return instance;
    }
    // 다른곳에서 객체 생성 금지
    private SingletonService(){
    }
}
