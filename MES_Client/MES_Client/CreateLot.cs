using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Net.Sockets;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace MES_Client
{
    public partial class CreateLot : Form
    {
        TcpClient client = null;
        NetworkStream ns = null; 
        StreamWriter writer = null;
        StreamReader reader = null;


        public CreateLot()
        {
            InitializeComponent();
            this.ActiveControl = textBoxLotId;

            //client = new TcpClient("localhost", 8000);
            //if (client.Connected) MessageBox.Show("Server Connected.");
            //ns = client.GetStream();
            //writer = new StreamWriter(ns);
            //reader = new StreamReader(ns);

            
        }
        
        private void CreateLot_Load(object sender, EventArgs e)
        {
            client = new TcpClient("localhost", 8000);
            //if (client.Connected) MessageBox.Show("Server Connected.");
            ns = client.GetStream();
            writer = new StreamWriter(ns);
            reader = new StreamReader(ns);

            //writer.WriteLine("get_combo");
            //writer.Flush();

            
            String request = "action=get_combo;para1=oper;para2=flow;para3=prod";
            writer.WriteLine(request);
            writer.Flush();

            fillOper();
            fillFlow();
            fillProd();
             
            
            /*
            String request = "action=get_oper";
            writer.WriteLine(request);
            writer.Flush();
            fillOper();

            request = "action=get_flow";
            writer.WriteLine(request);
            writer.Flush();
            fillFlow();

            request = "action=get_prod";
            writer.WriteLine(request);
            writer.Flush();
            fillProd();
             * */
            

            //fillOperTest();
            //or
            //bindCombo("action=get_oper", comboBoxOper);
            //bindCombo("action=get_flow", comboBoxFlow);
            //bindCombo("action=get_prod", comboBoxProd);
            
        }

        
        void fillOper() 
        {
            //writer.WriteLine("oper");
            //writer.Flush();
            String receive = reader.ReadLine();
            //MessageBox.Show(receive);
            string[] oper = receive.Split(',');
            for (int i = 0; i < oper.Length; i++)
            {
                comboBoxOper.Items.Add(oper[i]);
            }
        }
       

        /*
        void fillOperTest() 
        {
            writer.WriteLine("action=get_oper");

            // 나중에 서버로 보낼 데이터가 필요하다면 예시
            // writer.WriteLine("action=get_oper;lot=lot1;flow=flow1");
            writer.Flush();
            String receive = reader.ReadLine();
            //MessageBox.Show(receive);
            string[] oper = receive.Split(',');

            // 코드는 풀어서 표현한다
            for (int i = 0; i < oper.length; i++)
            {
                comboBoxOper.Items.Add(oper[i]);
            }
        }
         * */
        /*
        void bindCombo(string strPara, ComboBox cbBox) 
        {

            writer.WriteLine(strPara);
            writer.Flush();

            // 나중에 서버로 보낼 데이터가 필요하다면 예시
            // writer.WriteLine("action=get_oper;lot=lot1;flow=flow1");

            String receive = reader.ReadLine();
            //MessageBox.Show(receive);
            string[] receives = receive.Split(',');

            // 코드는 풀어서 표현한다
            for (int i = 0; i < receives.Length; i++)
            {
                cbBox.Items.Add(receives[i]);
            }
        }
        */ 

        
        void fillFlow()
        {
           // writer.WriteLine("flow");
           // writer.Flush();
            String receive = reader.ReadLine();
            //MessageBox.Show(receive);
            string[] flow = receive.Split(',');
            for (int i = 0; i < flow.Length; i++) {
                comboBoxFlow.Items.Add(flow[i]);
            }
            //foreach (string s in oper) comboBoxFlow.Items.Add(s);
        }


        void fillProd()
        {
            //writer.WriteLine("prod");
            //writer.Flush();
            String receive = reader.ReadLine();
            //MessageBox.Show(receive);
            string[] prod = receive.Split(',');
            for (int i = 0; i < prod.Length; i++)
            {
                comboBoxProd.Items.Add(prod[i]);
            }
            //foreach (string s in oper) comboBoxProd.Items.Add(s);
        }
        

        private void textBoxLotId_KeyPress(object sender, KeyPressEventArgs e)
        {
            if (!(Char.IsLetter(e.KeyChar)) && !(Char.IsDigit(e.KeyChar)) && e.KeyChar != 8) 
            {
                e.Handled = true;
            }
        }

        private void btnReset_Click(object sender, EventArgs e)
        {
            textBoxLotId.Text = String.Empty;
            textBoxProdQty.Text = String.Empty;
        }

        private void btnCreate_Click(object sender, EventArgs e)
        {
            client = new TcpClient("localhost", 8000);
            //if (client.Connected) MessageBox.Show("Server Connected.");
            ns = client.GetStream();
            writer = new StreamWriter(ns);
            reader = new StreamReader(ns);

            writer.WriteLine("action=create_lot");
            writer.Flush();

            create_lot();
        }

        void create_lot()
        {
            String id = textBoxLotId.Text.ToString().ToUpper();
            String oper = comboBoxOper.Text.ToString();
            String flow = comboBoxFlow.Text.ToString();
            String prod = comboBoxProd.Text.ToString();
            String prod_qty = textBoxProdQty.Text.ToString();

            String insertData = "'" + id + "','" + oper + "','" + flow + "','" + prod + "','" + prod_qty + "'";

            writer.WriteLine(id);
            writer.Flush();
            writer.WriteLine(insertData);
            writer.Flush();
            //MessageBox.Show(insertData);
            String receive = reader.ReadLine();
            MessageBox.Show(receive);
        }
    }
}
