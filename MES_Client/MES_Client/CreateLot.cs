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

            client = new TcpClient("localhost", 8000);
            if (client.Connected) MessageBox.Show("Server Connected.");
            ns = client.GetStream();
            writer = new StreamWriter(ns);
            reader = new StreamReader(ns);
        }
        
        private void CreateLot_Load(object sender, EventArgs e)
        {
            
            String selOper = labelOper.Text.ToString().ToLower();
            String selFlow = labelFlow.Text.ToString().ToLower();
            String selProd = labelProd.Text.ToString().ToLower();
            String receive = String.Empty;

            writer.WriteLine("search_combo");
            writer.Flush();

            writer.WriteLine(selOper);
            writer.Flush();
            //MessageBox.Show(selOper);
            receive = reader.ReadLine();
            //MessageBox.Show(receive);
            string[] oper = receive.Split(',');
            foreach (string s in oper) comboBoxOper.Items.Add(s);

            writer.WriteLine(selFlow);
            writer.Flush();
            //MessageBox.Show(selFlow);
            receive = reader.ReadLine();
            //MessageBox.Show(receive);
            string[] flow = receive.Split(',');
            foreach (string s in flow) comboBoxFlow.Items.Add(s);

            writer.WriteLine(selProd);
            writer.Flush();
            //MessageBox.Show(selProd);
            receive = reader.ReadLine();
            //MessageBox.Show(receive);
            string[] prod = receive.Split(',');
            foreach (string s in prod) comboBoxProd.Items.Add(s);

            writer.Close();
            reader.Close();
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
            String id = textBoxLotId.Text.ToString();
            String oper = comboBoxOper.Text.ToString();
            String flow = comboBoxFlow.Text.ToString();
            String prod = comboBoxProd.Text.ToString();
            String prod_qty = textBoxProdQty.Text.ToString();

            // String[] insertData = {id,oper,flow,prod,prod_qty};

            // foreach (String s in insertData) MessageBox.Show(s);
            String insertData = id + "," + oper + "," + flow + "," + prod + "," + prod_qty;
            writer.WriteLine("create_lot");
            writer.Flush();
            writer.WriteLine(insertData);
            writer.Flush();
            MessageBox.Show("insertData");
            String receive = reader.ReadLine();
            MessageBox.Show(receive);
        }
    }
}
