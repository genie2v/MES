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
        }
        
        private void CreateLot_Load(object sender, EventArgs e)
        {
            client = new TcpClient("localhost", 8000);
            //if (client.Connected) MessageBox.Show("Server Connected.");
            ns = client.GetStream();
            writer = new StreamWriter(ns);
            reader = new StreamReader(ns);

            writer.WriteLine("get_combo");
            writer.Flush();
            
            fillOper();
            fillFlow();
            fillProd();
            
            
            //String request = "action=get_combo;para1=oper;para2=flow;para3=prod";
            //writer.WriteLine(request);
            //writer.Flush();          
        }

        void fillOper() 
        {
            writer.WriteLine("oper");
            writer.Flush();
            String receive = reader.ReadLine();
            //MessageBox.Show(receive);
            string[] oper = receive.Split(',');
            foreach (string s in oper) comboBoxOper.Items.Add(s);
        }

        void fillFlow()
        {
            writer.WriteLine("flow");
            writer.Flush();
            String receive = reader.ReadLine();
            //MessageBox.Show(receive);
            string[] oper = receive.Split(',');
            foreach (string s in oper) comboBoxFlow.Items.Add(s);
        }

        void fillProd()
        {
            writer.WriteLine("prod");
            writer.Flush();
            String receive = reader.ReadLine();
            //MessageBox.Show(receive);
            string[] oper = receive.Split(',');
            foreach (string s in oper) comboBoxProd.Items.Add(s);
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

            writer.WriteLine("create_lot");
            writer.Flush();

            create_lot();
        }

        void create_lot()
        {
            String id = textBoxLotId.Text.ToString();
            String oper = comboBoxOper.Text.ToString();
            String flow = comboBoxFlow.Text.ToString();
            String prod = comboBoxProd.Text.ToString();
            String prod_qty = textBoxProdQty.Text.ToString();

            String insertData = "'" + id + "','" + oper + "','" + flow + "','" + prod + "','" + prod_qty + "'";

            writer.WriteLine(insertData);
            writer.Flush();
            MessageBox.Show(insertData);
            String receive = reader.ReadLine();
            MessageBox.Show(receive);
        }
    }
}
