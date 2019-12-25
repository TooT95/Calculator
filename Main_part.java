import javax.swing.text.html.parser.Parser;
import java.util.Scanner;

public class Main_part {
    // Создаем переменную который читает с консоли данные
    private static Scanner inner = new Scanner(System.in);

    private static Advanced advancedClass = new Advanced();
    private static int i;

    public static void main(String[] args) {
        // Считываем входные данные
        String textInput = inner.nextLine();
        // Инициализация позицию операции
        int indexOfOperation = -1;
        // Получим позицию операции
        indexOfOperation = advancedClass.indexOfOperation(textInput);
        if (indexOfOperation == -1)
        {
            System.out.println("Введена неправильная арифметическая операция!");
            return;
        }
        // Получим операцию
        String operation = advancedClass.Operation(textInput);
        String[] NumbersString = advancedClass.Numbers(textInput,indexOfOperation);
        if (NumbersString[0].trim().isEmpty() || NumbersString[1].trim().isEmpty())
        {
            System.out.println("Введена неправильные символы!");
            return;
        }
        //Проверим на арабскую и римские числа
        boolean isArabian = advancedClass.ArabianRome(NumbersString);

        // Получим числа
        int[] Numbers=new int[2];
        for (i=0;i<NumbersString.length;i++)
        {
            if (isArabian)
            {
                Numbers[i] = advancedClass.StringToNumber(NumbersString[i]);
            }else
            {
                Numbers[i] = advancedClass.StringToNumberRome(NumbersString[i]);
            }
            if(Numbers[i]==-1)
            {
                System.out.println("Числа введены больше чем надо!");
                return;
            }
        }

        int result = advancedClass.Execute(Numbers,operation);
        if(isArabian)
        {
            System.out.println(result);
        }
        else
        {
            String resultOfRome="";
            int partofD = (result%100)%10;
            int partofDD = (result%100)/10;
            boolean partofDDD = (result==100);
            if(partofDDD){
                System.out.println("C");
            }
            else {
                resultOfRome = resultOfRome + advancedClass.RomeDecimal(partofDD);
                resultOfRome = resultOfRome + advancedClass.RomeDigital(partofD);
                System.out.println(resultOfRome);
            }
        }
    }
}
class Advanced{
    // функция возвращает позицию операцию введенный пользователем
    public int indexOfOperation(String text){
        int indexOf = -1;
        indexOf = text.indexOf('+');
        if(indexOf==-1){
            indexOf = text.indexOf('-');
        }
        if(indexOf==-1){
            indexOf = text.indexOf('/');
        }if(indexOf==-1){
            indexOf = text.indexOf('*');
        }
        return indexOf;
    }

    // функция возвращает операцию введенный пользователем
    public String Operation(String text){
        String operation = "";
        int indexOf = -1;
        indexOf = text.indexOf('+'); operation = "+";
        if(indexOf==-1){
            indexOf = text.indexOf('-'); operation = "-";
        }
        if(indexOf==-1){
            indexOf = text.indexOf('/'); operation = "/";
        }if(indexOf==-1){
            indexOf = text.indexOf('*'); operation = "*";
        }
        return (indexOf==-1)?"":operation;
    }

    // функция возвращает массив который храниться числа введенный пользователем
    public String[] Numbers(String text,int operation){
        String[] numbers = new String[2];
        numbers[0] = text.substring(0,operation);
        numbers[1] = text.substring(operation+1,text.length());
        return numbers;
    }

    // функция возвращает число преобразованной от строки
    public int StringToNumberRome(String charOf){
        int result = -1;
        switch (charOf){
            case "I":result=1;break;
            case "II":result=2;break;
            case "III":result=3;break;
            case "IV":result=4;break;
            case "V":result=5;break;
            case "VI":result=6;break;
            case "VII":result=7;break;
            case "VIII":result=8;break;
            case "IX":result=9;break;
            case "X":result=10;break;
            default: result = -1;break;
        }
        return result;
    }

    // функция возвращает число преобразованной от римской строки
    public int StringToNumber(String charOf){
        int result = -1;
        result = Integer.parseInt(charOf);
        if(result>10)
            result = -1;
        return result;
    }
    // функия вычисляет по арифметическому операцию
    public int Execute(int[] Numbers,String operation )
    {
        int result;
        switch (operation){
            case "+": result = Numbers[0]+Numbers[1]; break;
            case "-": result = Numbers[0]-Numbers[1]; break;
            case "*": result = Numbers[0]*Numbers[1]; break;
            case "/": result = Numbers[0]/Numbers[1]; break;
            default:
                throw new IllegalStateException("Неправильная операция: " + operation);
        }
        return result;
    }

    //Функция возвращает проверки значения на арабскую или римскую
    public boolean ArabianRome(String[] numbers)
    {
        boolean isArabian = false;
       isArabian = ( -1 == numbers[0].indexOf("I")&&
                -1 == numbers[0].indexOf("V")&&
                -1 == numbers[0].indexOf("X"))&&
               ( -1 == numbers[1].indexOf("I")&&
                       -1 == numbers[1].indexOf("V")&&
                       -1 == numbers[1].indexOf("X"));
        return isArabian;
    }

    //Функция возвращает значения римских единиц
    public String RomeDigital(int number)
    {
        String result = "";
        switch (number){
            case 0:result="";break;
            case 1:result="I";break;
            case 2:result="II";break;
            case 3:result="III";break;
            case 4:result="IV";break;
            case 5:result="V";break;
            case 6:result="VI";break;
            case 7:result="VII";break;
            case 8:result="VIII";break;
            case 9:result="IX";break;
            default: result = "";break;
        }
        return result;
    }

    //Функция возвращает значения римских десятков
    public String RomeDecimal(int number)
    {
        String result = "";
        switch (number){
            case 0:result="";break;
            case 1:result="X";break;
            case 2:result="XX";break;
            case 3:result="XXX";break;
            case 4:result="XL";break;
            case 5:result="L";break;
            case 6:result="LX";break;
            case 7:result="LXX";break;
            case 8:result="LXXX";break;
            case 9:result="XC";break;
            default: result = "";break;
        }
        return result;
    }
}