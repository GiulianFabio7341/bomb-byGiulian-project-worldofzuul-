
public class Item {
        private String descrição;



        private int peso;

        public Item(String informação,int peso) {
            this.descrição = informação;
            this.peso=peso;
        }
    public int getPeso() {
        return peso;
    }

    public String getDescrição() {
            return descrição;
    }

    public void setDescrição(String descrição) {
            this.descrição = descrição;
        }

        public void setPeso(int peso) {
            this.peso = peso;
        }

        public String getInformação(){
            System.out.print("OBJETOS DA SALA:");
            System.out.println(descrição);
            System.out.print("PESO:");
            System.out.println(peso+"kg");

            return "";
        }

    }


