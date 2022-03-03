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
            /*
            String request = "action=get_combo;para1=oper;para2=flow;para3=prod";
            writer.WriteLine(request);
            writer.Flush();

            fillOper();
            fillFlow();
            fillProd();
             * */
             
            
            String request = "action=get_oper";
            queryWriter.WriteLine(request);
            queryWriter.Flush();
            fillOper();

            request = "action=get_flow";
            queryWriter.WriteLine(request);
            queryWriter.Flush();
            fillFlow();

            request = "action=get_prod";
            queryWriter.WriteLine(request);
            queryWriter.Flush();
            fillProd();
            
            check
            // 위처럼 동일한 코드가 반복되는 경우
            // 이 주석처럼 재사용 코드로 간결하게 표현할 수 있다
            //bindCombo("action=get_oper", comboBoxOper);
            //bindCombo("action=get_flow", comboBoxFlow);
            //bindCombo("action=get_prod", comboBoxProd);
        }
        
        void fillOper() 
        {
            String receive = queryReader.ReadLine();
            //MessageBox.Show(receive);
            string[] oper = receive.Split(',');
            for (int i = 0; i < oper.Length; i++)
            {
                comboBoxOper.Items.Add(oper[i]);
            }
        }
       
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
            String receive = queryReader.ReadLine();
            //MessageBox.Show(receive);
            string[] flow = receive.Split(',');
            for (int i = 0; i < flow.Length; i++) {
                comboBoxFlow.Items.Add(flow[i]);
            }
        }

        void fillProd()
        {
            String receive = queryReader.ReadLine();
            //MessageBox.Show(receive);
            string[] prod = receive.Split(',');
            for (int i = 0; i < prod.Length; i++)
            {
                comboBoxProd.Items.Add(prod[i]);
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


            check
            // DAO DTO 를 적용하면 바뀌어야할 부분
            // name=value 형식으로 넘기도록 해야 한다
            // ex) LOT_ID=LOT00001;OPER=1000;FLOW=FLOW-0001;PROD=PROD-XXXX-0001;PROD_QTY=1234

            String insertData = "'" + id + "','" + oper + "','" + flow + "','" + prod + "','" + prod_qty + "'";

            wipWriter.WriteLine(id);
            wipWriter.Flush();
            wipWriter.WriteLine(insertData);
            wipWriter.Flush();
            //MessageBox.Show(insertData);
            String receive = wipReader.ReadLine();
            MessageBox.Show(receive);
        }
    }
}
