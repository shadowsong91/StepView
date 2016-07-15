# StepView
Step by step，just use HorizontalStepDecoration，VerticalStepDecoration. step indicator，flow indicator，timeline，order process，express status
# ATTENTION
This library is NOT a view, but a ItemDecoration for RecyclerView.
# How to use
```
// get a RecyclerView
recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
HorizontalStepDecoration stepDecoration = new HorizontalStepDecoration.Builder(this)//or VerticalStepDecoration
            .currentRes(R.drawable.step_current)
            .doneRes(R.drawable.step_done)
            .undoneRes(R.drawable.step_undone)
            .height(200)
            .lineWidth(3)
            .lineColor(0xff11cd6e)
            .dashColor(0xff11cd6e)
            .currentStep(25)
            .build();
recyclerView.addItemDecoration(stepDecoration);
```
That's all code!
You can run the example to see more.

# Screen shot
![image](https://github.com/shadowsong91/StepView/raw/master/screenshot/stepView-shot.png)

# Thanks
Idea from [StepView](https://github.com/baoyachi/StepView)  
Icons from [iconfont](http://iconfont.cn/)
