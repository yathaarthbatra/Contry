package com.example.splitwise;

public class SplitAlgo {

    private int numberOfPeople = 0;

    private int getMinNetAmountIndex(int[] netAmount){
        int minIndex = 0;
        int minValue = Integer.MAX_VALUE;
        for(int i=0;i<netAmount.length;i++){
            if(netAmount[i] < minValue ){
                minValue = netAmount[i];
                minIndex = i;
            }
        }
        return minIndex;
    }
    private int getMaxNetAmountIndex(int[] netAmount){
        int maxIndex = 0;
        int maxValue = Integer.MIN_VALUE;
        for(int i=0 ;i<netAmount.length ; i++){
            if(netAmount[i] > maxValue){
                maxValue = netAmount[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    private int getMin(int a ,int b){
        return Math.min(a,b);
    }

    public String getResult(int[] netAmount, String result,Person[] persons){

        //denotes the index of the maximum credit netAmount Array
        int maxCreditIndex = getMaxNetAmountIndex(netAmount);

        //denotes the index of the maximum debit
        int maxDebitIndex = getMinNetAmountIndex(netAmount);

        //min of the maxCredit Amount and maxDebit Amount
        int minAmount = getMin(netAmount[maxCreditIndex],-netAmount[maxDebitIndex]);

        //if the maxDebit and MAxCredit amounts are 0 then no need to settle
        if(netAmount[maxCreditIndex]==0 && netAmount[maxDebitIndex]==0){
            return result;
        }

        //updating the netAmount Array acc to the logic of min
        netAmount[maxCreditIndex] -= minAmount;
        netAmount[maxDebitIndex] += minAmount;

        result = result + "\n" + persons[maxDebitIndex].getName() + " pays " + minAmount + " to " + persons[maxCreditIndex].getName();

        //Applying recursion to leftOver netAmount Array
        String finalResult = getResult(netAmount,result,persons);

        return finalResult;
    }

    public void setNumberPeople(int n){
        numberOfPeople = n;
    }

    public String takeInput(int[][] graph,Person[] list){

        //setting netAmount to 0 initially
        int []netAmount = new int[numberOfPeople];

        //calculating the netAmount of the Vertices
        //netAmount = Total Credit - Total Debit

        //graph[i][j] means amount i has to pay j

        for(int p=0; p<numberOfPeople;p++){
            for(int i=0; i<numberOfPeople;i++){
                netAmount[p] += (graph[i][p] - graph[p][i]);
            }
        }



        String finalOutputText = getResult(netAmount,"",list);

        return finalOutputText;


    }

}
