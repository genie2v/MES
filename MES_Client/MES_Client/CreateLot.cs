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
        TcpClient tcWip = null;
        NetworkStream wipNs = null;
        StreamWriter wipWriter = null;
        StreamReader wipReader = null;

        TcpClient tcQuery = null;
        NetworkStream queryNs = null;
        StreamWriter queryWriter = null;
        StreamReader queryReader = null;

        public CreateLot()
        {
            InitializeComponent();
        }

        public CreateLot(TcpClient wip, TcpClient query)
        {
            // 이미 connection 맺은 소켓 연결을 가지고 사용
            InitializeComponent();
            this.ActiveControl = textBoxLotId;

            tcWip = wip;
            tcQuery = query;

            wipNs = tcWip.GetStream();
            wipWriter = new StreamWriter(wipNs);
            wipReader = new StreamReader(wipNs);

            queryNs = tcQuery.GetStream();
            queryWriter = new StreamWriter(queryNs);
            queryReader = new StreamReader(queryNs);
        }
        
        private void CreateLot_Load(object sender, EventArgs e)
        {
            bindCombo("action=get_oper", comboBoxOper);
            bindCombo("action=get_flow", comboBoxFlow);
            bindCombo("action=get_prod", comboBoxProd);
        }

        
        void bindCombo(string strPara, ComboBox cbBox) 
        {
            queryWriter.WriteLine(strPara);
            queryWriter.Flush();

            String receive = queryReader.ReadLine();
            String[] bindData = receive.Split(',');

            for (int i = 0; i < bindData.Length; i++)
            {
                cbBox.Items.Add(bindData[i]);
            }
            cbBox.SelectedIndex = 0;
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
            wipWriter.WriteLine("action=create_lot");
            wipWriter.Flush();

            create_lot();
        }

        void create_lot()
        {
            String id = textBoxLotId.Text.ToString().ToUpper();
            String oper = comboBoxOper.Text.ToString();
            String flow = comboBoxFlow.Text.ToString();
            String prod = comboBoxProd.Text.ToString();
            String prod_qty = textBoxProdQty.Text.ToString();

            String insertData = "lot="+id+";oper="+oper+";flow="+flow+";prod="+prod+";prod_qty="+prod_qty;

            wipWriter.WriteLine(insertData);
            wipWriter.Flush();
            String receive = wipReader.ReadLine();
            MessageBox.Show(receive);
        }
    }
}
