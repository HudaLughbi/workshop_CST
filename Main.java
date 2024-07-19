package comp8190.spacedomain;

public class Main {
    public static void main(String[] args) {
        SensorStateMachine sensorStateMachine = new SensorStateMachine();
        
        // تسلسل الحالة عند الوضع الطبيعي
        sensorStateMachine.detectMsg();
        sensorStateMachine.analyzeMsg();
        sensorStateMachine.request();
        sensorStateMachine.readRequest();
        sensorStateMachine.sensors(false);
        sensorStateMachine.normal();
        sensorStateMachine.situationAnalyses();
        sensorStateMachine.readStatus();
        sensorStateMachine.manageMsg();
        sensorStateMachine.delete();
        
        System.out.println("Final state: " + sensorStateMachine.getCurrentState());
        System.out.println("Messages: " + sensorStateMachine.getMessages());

        // تسلسل الحالة عند وجود تسرب يتطلب تدخل
        sensorStateMachine = new SensorStateMachine();
        
        sensorStateMachine.detectMsg();
        sensorStateMachine.analyzeMsg();
        sensorStateMachine.request();
        sensorStateMachine.readRequest();
        sensorStateMachine.sensors(true);
        sensorStateMachine.spil(true);
        sensorStateMachine.situationAnalyses();
        sensorStateMachine.readStatus();
        sensorStateMachine.manageMsg();
        sensorStateMachine.delete();
        
        System.out.println("Final state with spill requiring intervention: " + sensorStateMachine.getCurrentState());
        System.out.println("Messages: " + sensorStateMachine.getMessages());

        // تسلسل الحالة عند وجود تسرب لا يتطلب تدخل
        sensorStateMachine = new SensorStateMachine();
        
        sensorStateMachine.detectMsg();
        sensorStateMachine.analyzeMsg();
        sensorStateMachine.request();
        sensorStateMachine.readRequest();
        sensorStateMachine.sensors(true);
        sensorStateMachine.spil(false);
        sensorStateMachine.situationAnalyses();
        sensorStateMachine.readStatus();
        sensorStateMachine.manageMsg();
        sensorStateMachine.delete();
        
        System.out.println("Final state with spill not requiring intervention: " + sensorStateMachine.getCurrentState());
        System.out.println("Messages: " + sensorStateMachine.getMessages());
    }
}
