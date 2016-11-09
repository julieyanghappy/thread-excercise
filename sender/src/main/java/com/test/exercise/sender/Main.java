package com.test.exercise.sender;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

        //用户输入n个字符
        Scanner scanner = new Scanner(System.in);
        ArrayList list = new ArrayList();
        SendData sdClass=new SendData();
        int ThreadNum=0;//线程数
        do
        {
            System.out.println("请输入数字，按Q或q表示结束输入");
            String inNum = scanner.next();
            if(inNum==null||inNum.trim().length()==0)
            {
                System.out.println("请输入数字，按Q或q表示结束输入。");
                continue;
            }
            if (inNum.toUpperCase().equals("Q") ) {
                break;
            }

            if (sdClass.isNumeric(inNum)) {
                list.add(inNum);
            }
            else {
                System.out.println("请输入数字。");
            }
        }
        while (true);

        do{
            System.out.println("请输入要启动的线程数");
            String threadNum = scanner.next();
            if(threadNum==null||threadNum.trim().length()==0)
            {
                System.out.println("线程不能为空且大于0");
                continue;
            }

            if (sdClass.isNumeric(threadNum)&&Integer.parseInt(threadNum)>0) {
                ThreadNum=Integer.parseInt(threadNum);
                break;
            }
            else {
                System.out.println("请输入大于0的数字。");
                continue;
            }
        }
        while (true);

         //启动发送任务
        int workcount=ThreadNum>list.size()?list.size():ThreadNum;//线程路不超过数组的大小
        WorkerContainer wc=WorkerContainer.getInstance();
        wc.init(workcount,list);
        wc.start();

    }
}
