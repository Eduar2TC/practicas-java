package RegresionLineal;

import org.apache.commons.math3.stat.regression.SimpleRegression;
import org.jfree.data.xy.XYSeries;

class GetResults {

    public XYSeries getSeries() {
        XYSeries series = new XYSeries("Series");
        for (int i = 0; i < 10; i++) {
            series.add(i, Math.pow(2, i));
        }
        return series;
    }
}

public class Try_Regression {

    public static void main(String[] args) {

        // creating regression object, passing true to have intercept term
        SimpleRegression simpleRegression = new SimpleRegression(true);

        // passing data to the model
        // model will be fitted automatically by the class 
        simpleRegression.addData(new double[][] {
                {1, 2},
                {2, 3},
                {2, 4},
                {3, 4},
                {4, 4},
                {4,6},
                {5,5},
                {6,7},
                
        });

        // querying for model parameters
        System.out.println("pendiente = " + simpleRegression.getSlope());
        System.out.println("interseccion = " + simpleRegression.getIntercept());

        // trying to run model for unknown data
        System.out.println("prediccion para 6 = " + simpleRegression.predict(6));


    }

}