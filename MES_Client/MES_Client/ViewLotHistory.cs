using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Net.Sockets;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace MES_Client
{
    public partial class ViewLotHistory : Form
    {
        TcpClient client = null;
        NetworkStream ns = null;
        StreamWriter writer = null;
        StreamReader reader = null;
        DataTable dataTable = new DataTable();

        public ViewLotHistory()
        {
            InitializeComponent();

            dataTable.Columns.Add("LOT", typeof(String));
            dataTable.Columns.Add("Oper", typeof(String));
            dataTable.Columns.Add("Flow", typeof(String));
            dataTable.Columns.Add("Prod", typeof(String));
            dataTable.Columns.Add("Prod Qty", typeof(String));

            dataGridView1.DataSource = dataTable;
        }

        private void buttonSearch_Click(object sender, EventArgs e)
        {
            client = new TcpClient("localhost", 8000);
            //if (client.Connected) MessageBox.Show("Server Connected.");
            ns = client.GetStream();
            writer = new StreamWriter(ns);
            reader = new StreamReader(ns);

            //writer.WriteLine("get_his");
            //writer.Flush();
            String lotId = textBoxSearch.Text.ToString().ToUpper();
            writer.WriteLine("action=get_his;para1="+lotId);
            writer.Flush();
            getHis();
        }

        void getHis() 
        {
            //String lotId = textBoxSearch.Text.ToString();

            //writer.WriteLine(lotId);
            //writer.Flush();
            //MessageBox.Show(lotId);
            String count = reader.ReadLine();
            //MessageBox.Show(count);
            String receive = "";
            dataTable.Rows.Clear();
            dataGridView1.DataSource = dataTable;
            for(int i =0; i<Convert.ToInt16(count);i++)
            {
                receive = reader.ReadLine();
                //MessageBox.Show(receive);
                String[] his = receive.Split(',');
                //foreach (String s in his) MessageBox.Show(s);
                dataTable.Rows.Add(his[0], his[1], his[2], his[3], his[4]);
                dataGridView1.DataSource = dataTable;
            }
        }
    }
}
