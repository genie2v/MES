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
    public partial class UndoLot : Form
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

        public UndoLot()
        {
            InitializeComponent();
        }

        public UndoLot(TcpClient wip, TcpClient query)
        {
            InitializeComponent();

            dataTable.Columns.Add("LOT", typeof(String));
            dataTable.Columns.Add("Oper", typeof(String));
            dataTable.Columns.Add("Flow", typeof(String));
            dataTable.Columns.Add("Prod", typeof(String));
            dataTable.Columns.Add("Prod Qty", typeof(String));
            dataTable.Columns.Add("Proc", typeof(String));
            dataTable.Columns.Add("TXN_CD", typeof(String));

            dataGridView1.DataSource = dataTable;

            tcWip = wip;
            tcQuery = query;

            wipNs = tcWip.GetStream();
            wipWriter = new StreamWriter(wipNs);
            wipReader = new StreamReader(wipNs);

            queryNs = tcQuery.GetStream();
            queryWriter = new StreamWriter(queryNs);
            queryReader = new StreamReader(queryNs);

        }

        private void buttonSearch_Click(object sender, EventArgs e)
        {
            String lotId = textBoxSearch.Text.ToUpper();
            queryWriter.WriteLine("action=get_his;lot_id=" + lotId + ";orderby=desc");
            queryWriter.Flush();
            getHis();     
        }

        void getHis()
        {
            String count = queryReader.ReadLine();
            dataTable.Rows.Clear();
            dataGridView1.DataSource = dataTable;
            for (int i = 0; i < Convert.ToInt16(count); i++)
            {
                String receive = queryReader.ReadLine();
                String[] his = receive.Split(',');
                dataTable.Rows.Add(his[0], his[1], his[2], his[3], his[4], his[5], his[6]);
                dataGridView1.DataSource = dataTable;
            }
        }

        private void btnRun_Click(object sender, EventArgs e)
        {
            String lotId = textBoxSearch.Text.ToUpper();
            wipWriter.WriteLine("action=undo_lot;lot_id=" + lotId);
            wipWriter.Flush();

            String receive = wipReader.ReadLine();
            MessageBox.Show(receive);
        }
    }
}
