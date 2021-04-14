package com.lge.ex23;


class Button {
    void setOnClickListener(OnClickListener listener) {
        // ...
    }
}

class Activity {
    private Button button;

    void onCreate() {
        // Anonymous Object
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick() {
                startActivity(Activity.this);
            }
        });

        // Lambda
        button.setOnClickListener(() -> {
            startActivity(this);
        });


    }

    void startActivity(Activity activity) {

    }
}


public class Sample {
    public static void main(String[] args) {
        Car car = new Car();

        // car.setName("Bob");
        // car.getName();
    }
}
