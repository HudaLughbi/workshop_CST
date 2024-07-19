package comp8190.spacedomain;

import java.util.LinkedList;
import java.util.Queue;

public class SensorStateMachine {
    private SensorState currentState;
    private Queue<String> messages; // قائمة الرسائل لتتبعها

    public SensorStateMachine() {
        currentState = SensorState.DETECT_MSG; // الحالة الابتدائية
        messages = new LinkedList<>();
    }

    public void detectMsg() {
        if (currentState == SensorState.DETECT_MSG) {
            currentState = SensorState.ANALYZE_MSG;
            messages.add("Detected message");
            System.out.println("Transition to ANALYZE_MSG");
        }
    }

    public void analyzeMsg() {
        if (currentState == SensorState.ANALYZE_MSG) {
            currentState = SensorState.REQUEST;
            messages.add("Analyzed message");
            System.out.println("Transition to REQUEST");
        }
    }

    public void request() {
        if (currentState == SensorState.REQUEST) {
            currentState = SensorState.READ_REQUEST;
            messages.add("Request made");
            System.out.println("Transition to READ_REQUEST");
        }
    }

    public void readRequest() {
        if (currentState == SensorState.READ_REQUEST) {
            currentState = SensorState.SENSORS;
            messages.add("Request read");
            System.out.println("Transition to SENSORS");
        }
    }

    public void sensors(boolean isSpil) {
        if (currentState == SensorState.SENSORS) {
            if (isSpil) {
                currentState = SensorState.SPIL;
                messages.add("Spil detected");
                System.out.println("Transition to SPIL");
            } else {
                currentState = SensorState.NORMAL;
                messages.add("No spil detected, condition is normal");
                System.out.println("Transition to NORMAL");
            }
        }
    }

    public void normal() {
        if (currentState == SensorState.NORMAL) {
            currentState = SensorState.NO_SPIL_NORMAL;
            messages.add("Condition is normal");
            System.out.println("Transition to NO_SPIL_NORMAL");
        }
    }

    public void spil(boolean requiresIntervention) {
        if (currentState == SensorState.SPIL) {
            if (requiresIntervention) {
                currentState = SensorState.SPIL_INTERVENTION;
                messages.add("Spil detected, requires intervention");
                System.out.println("Transition to SPIL_INTERVENTION");
            } else {
                currentState = SensorState.SPIL_NO_INTERVENTION;
                messages.add("Spil detected, no intervention needed");
                System.out.println("Transition to SPIL_NO_INTERVENTION");
            }
        }
    }

    public void situationAnalyses() {
        if (currentState == SensorState.NO_SPIL_NORMAL || 
            currentState == SensorState.SPIL_NO_INTERVENTION || 
            currentState == SensorState.SPIL_INTERVENTION) {
            currentState = SensorState.SITUATION_ANALYSES;
            messages.add("Situation analyzed");
            System.out.println("Transition to SITUATION_ANALYSES");
        }
    }

    public void readStatus() {
        if (currentState == SensorState.SITUATION_ANALYSES) {
            currentState = SensorState.READ_STATUS;
            messages.add("Status read");
            System.out.println("Transition to READ_STATUS");
        }
    }

    public void manageMsg() {
        if (currentState == SensorState.READ_STATUS) {
            currentState = SensorState.MANAGE_MSG;
            messages.add("Messages managed");
            System.out.println("Transition to MANAGE_MSG");
        }
    }

    public void delete() {
        if (currentState == SensorState.MANAGE_MSG) {
            currentState = SensorState.DELETE;
            // حذف الرسائل الأقدم
            while (messages.size() > 5) { // على سبيل المثال، الاحتفاظ بآخر 5 رسائل فقط
                messages.poll();
            }
            System.out.println("Transition to DELETE, older messages deleted");
        }
    }

    public SensorState getCurrentState() {
        return currentState;
    }

    public Queue<String> getMessages() {
        return messages;
    }
}
