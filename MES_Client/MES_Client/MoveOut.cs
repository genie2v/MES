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
    public partial class MoveOut : Form
    {
        TcpClient tcWip = null;
        NetworkStream wipNs = null;
        StreamWriter wipWriter = null;
        StreamReader wipReader = null;

        TcpClient tcQuery = null;
        NetworkStream queryNs = null;
        StreamWriter queryWriter = null;
        StreamReader queryReader = null;

        public MoveOut()
        {
            InitializeComponent();
        }

        public MoveOut(TcpClient wip, TcpClient query)
        {
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

        private void btnReset_Click(object sender, EventArgs e)
        {
            textBoxLotId.Text = String.Empty;
            textBoxOper.Text = String.Empty;
            textBoxFlow.Text = String.Empty;
            textBoxProd.Text = String.Empty;
            textBoxProdQty.Text = String.Empty;
        }

        private void textBoxLotId_KeyDown(object sender, KeyEventArgs e)
        {
            if (e.KeyCode == Keys.Enter)
            {
                String lotId = textBoxLotId.Text.ToString().ToUpper();
                queryWriter.WriteLine("action=get_lotinf;lot_id=" + lotId);
                queryWriter.Flush();

                getLotInf();
            }
        }

        void getLotInf()
        {
            String receive = queryReader.ReadLine();
            //MessageBox.Show(receive);
            String[] lotInf = receive.Split(';');
            for (int i = 0; i < lotInf.Length; i++)
            {
                String[] inf = lotInf[i].Split('=');
                for (int j = 0; j < inf.Length; j++)
                {
                    if (inf[j].Equals("oper"))
                    {
                        textBoxOper.Text = inf[1];
                        break;
                    }
                    else if (inf[j].Equals("flow"))
                    {
                        textBoxFlow.Text = inf[1];
                        break;
                    }
                    else if (inf[j].Equals("prod"))
                    {
                        textBoxProd.Text = inf[1];
                        break;
                    }
                    else if (inf[j].Equals("prod_qty"))
                    {
                        textBoxProdQty.Text = inf[1];
                        break;
                    }
                }
            }
        }

        private void btnRun_Click(object sender, EventArgs e)
        {
            String lotId = textBoxLotId.Text.ToString().ToUpper();
            wipWriter.WriteLine("action=moveout;lot_id=" + lotId);
            wipWriter.Flush();

            String receive = wipReader.ReadLine();
            MessageBox.Show(receive);
        }

    }
}
