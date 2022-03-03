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
        DataTable dataTable = new DataTable();

        TcpClient tcWip = null;
        NetworkStream wipNs = null;
        StreamWriter wipWriter = null;
        StreamReader wipReader = null;

        TcpClient tcQuery = null;
        NetworkStream queryNs = null;
        StreamWriter queryWriter = null;
        StreamReader queryReader = null;

        public ViewLotHistory()
        {
            InitializeComponent();
        }

        public ViewLotHistory(TcpClient wip, TcpClient query)
        {
            InitializeComponent();

            dataTable.Columns.Add("LOT", typeof(String));
            dataTable.Columns.Add("Oper", typeof(String));
            dataTable.Columns.Add("Flow", typeof(String));
            dataTable.Columns.Add("Prod", typeof(String));
            dataTable.Columns.Add("Prod Qty", typeof(String));

            dataGridView1.DataSource = dataTable;

            tcQuery = query;
            tcWip = wip;

            wipNs = tcWip.GetStream();
            wipWriter = new StreamWriter(wipNs);
            wipReader = new StreamReader(wipNs);

            queryNs = tcQuery.GetStream();
            queryWriter = new StreamWriter(queryNs);
            queryReader = new StreamReader(queryNs);
        }

        private void buttonSearch_Click(object sender, EventArgs e)
        {
            String lotId = textBoxSearch.Text.ToString().ToUpper();
            queryWriter.WriteLine("action=get_his;para1=" + lotId);
            queryWriter.Flush();
            getHis();
        }

        void getHis() 
        {
            String count = queryReader.ReadLine();
            //MessageBox.Show(count);
            //String receive = "";
            dataTable.Rows.Clear();
            dataGridView1.DataSource = dataTable;
            for(int i =0; i<Convert.ToInt16(count);i++)
            {
                String receive = queryReader.ReadLine();
                //MessageBox.Show(receive);
                String[] his = receive.Split(',');
                dataTable.Rows.Add(his[0], his[1], his[2], his[3], his[4]);
                dataGridView1.DataSource = dataTable;
            }
        }
    }
}
