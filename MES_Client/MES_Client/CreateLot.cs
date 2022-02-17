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
            //if (client.Connected) MessageBox.Show("Server Connected.");
            ns = client.GetStream();
            writer = new StreamWriter(ns);
            reader = new StreamReader(ns);
        }
        //void fillOper() 
        //{
        //    String selOper = labelOper.Text.ToString().ToLower();
        //    writer.WriteLine(selOper);
        //    writer.Flush();
        //    //MessageBox.Show(selOper + "를 전송");
        //    //MessageBox.Show(selOper);
        //    while (true)
        //    {
        //        receive = reader.ReadLine();
        //        if (receive == null) break;
        //        comboBoxOper.Items.Add(receive);
        //        //MessageBox.Show(receive);
        //    }
        //}
        //void fillFlow()
        //{
        //    String selFlow = labelFlow.Text.ToString().ToLower();
        //    writer.WriteLine(selFlow);
        //    writer.Flush();
        //    MessageBox.Show(selFlow);
        //    while (true)
        //    {
        //        receive = reader.ReadLine();
        //        if (receive == null) break;
        //        comboBoxFlow.Items.Add(receive);
        //    }
        //}
        private void CreateLot_Load(object sender, EventArgs e)
        {
            //fillOper();
            //fillFlow();
            String selOper = labelOper.Text.ToString().ToLower();
            String selFlow = labelFlow.Text.ToString().ToLower();
            String selProd = labelProd.Text.ToString().ToLower();

            String[] combo = { selOper, selFlow, selProd };

            foreach (String s in combo) 
            {
                // selFlow로 안넘어감 // while문 탈출이 안됨
                writer.WriteLine(s);
                writer.Flush();
                MessageBox.Show(s);
                // 임시방편으로 탈출을 위한 i
                int i = 0;
                while (true)
                {
                    i++;
                    String receive = reader.ReadLine();
                    
                    MessageBox.Show(receive);
                    if (s == "oper")
                    {
                        comboBoxOper.Items.Add(receive);
                    }
                    else if (s == "flow")
                    {
                        comboBoxFlow.Items.Add(receive);
                    }
                    else if (s == "prod")
                    {
                        comboBoxProd.Items.Add(receive);
                    }
                    if (i == 3) break;
                }
                MessageBox.Show("while문 탈출");
            }
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

            String[] insertData = {id,oper,flow,prod,prod_qty};

            foreach (String s in insertData) MessageBox.Show(s);
        }
    }
}
